package com.projetointegrador.cursos.integration;

import com.projetointegrador.cursos.infrastructure.entity.Curso;
import com.projetointegrador.cursos.infrastructure.repository.AreaConhecimentoRepository;
import com.projetointegrador.cursos.infrastructure.repository.CampusRepository;
import com.projetointegrador.cursos.infrastructure.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CursoCadastroIntegrationTest {

    private static final String NOME_NOVO = "Curso Teste Integração";
    private static final String ACRONIMO_NOVO = "CTESTE";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CampusRepository campusRepository;

    @Autowired
    private AreaConhecimentoRepository areaConhecimentoRepository;

    private Long campusId;
    private Long areaId;
    private String nomeExistente;
    private String acronimoExistente;

    @BeforeEach
    void setUp() {
        // GIVEN (comum): dados que já vêm do seed
        campusId = campusRepository.findAll().stream()
                .filter(c -> c.getAcronimo().equals("IFNC"))
                .findFirst().orElseThrow().getId();

        areaId = areaConhecimentoRepository.findAll().stream()
                .filter(a -> a.getNome().equals("Informação e Comunicação"))
                .findFirst().orElseThrow().getId();

        Curso tads = cursoRepository.findAll().stream()
                .filter(c -> c.getAcronimo().equals("TADS") && c.getCampus().getId().equals(campusId))
                .findFirst().orElseThrow();

        nomeExistente = tads.getNome();
        acronimoExistente = tads.getAcronimo();
    }

    private String montarJson(String nome, String acronimo, Long campusId, Long areaId) {
        return """
                {
                  "nome": "%s",
                  "acronimo": "%s",
                  "grau": "TECNOLOGO",
                  "duracao": "6 semestres",
                  "turno": "NOTURNO",
                  "modalidade": "PRESENCIAL",
                  "cargaHoraria": 2000,
                  "qtdDisciplinasObrigatorias": 30,
                  "qtdDisciplinasOptativas": 5,
                  "descricao": "Curso criado pelo teste de integração",
                  "campusId": %d,
                  "areaId": %d
                }
                """.formatted(nome, acronimo, campusId, areaId);
    }

    @Test
    void deveCadastrarCursoEPersistirNoBanco() throws Exception {
        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(montarJson(NOME_NOVO, ACRONIMO_NOVO, campusId, areaId)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value(NOME_NOVO))
                .andExpect(jsonPath("$.acronimo").value(ACRONIMO_NOVO))
                .andExpect(jsonPath("$.campusNome").value("Campus Nova Cruz"))
                .andExpect(jsonPath("$.areaNome").value("Informação e Comunicação"))
                .andExpect(jsonPath("$.coordenadorId").doesNotExist());

        assertThat(cursoRepository.existsByCampusIdAndNomeIgnoreCase(campusId, NOME_NOVO)).isTrue();
    }

    @Test
    void deveRetornarConflitoAoCadastrarCursoComNomeRepetidoNoCampus() throws Exception {
        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(montarJson(nomeExistente, ACRONIMO_NOVO, campusId, areaId)))
                .andExpect(status().isConflict());
    }

    @Test
    void deveRetornarConflitoAoCadastrarCursoComAcronimoRepetidoNoCampus() throws Exception {
        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(montarJson(NOME_NOVO, acronimoExistente, campusId, areaId)))
                .andExpect(status().isConflict());
    }

    @Test
    void deveRetornarNaoEncontradoAoCadastrarCursoEmCampusInexistente() throws Exception {
        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(montarJson(NOME_NOVO, ACRONIMO_NOVO, 999999L, areaId)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarRequisicaoInvalidaAoCadastrarCursoSemNome() throws Exception {
        long totalAntes = cursoRepository.count();

        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(montarJson("", ACRONIMO_NOVO, campusId, areaId)))
                .andExpect(status().isBadRequest());

        assertThat(cursoRepository.count()).isEqualTo(totalAntes);
    }
}
