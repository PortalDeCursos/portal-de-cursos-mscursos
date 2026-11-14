package com.projetointegrador.cursos.integration;

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

    private static final String NOME_NOVO = "Sistemas para Internet";
    private static final String ACRONIMO_NOVO = "TSI";

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

    @BeforeEach
    void setUp() {
        // GIVEN (comum): campus e área que já vêm da migration de seed
        campusId = campusRepository.findAll().stream()
                .filter(c -> c.getAcronimo().equals("IFNC"))
                .findFirst().orElseThrow().getId();

        areaId = areaConhecimentoRepository.findAll().stream()
                .filter(a -> a.getNome().equals("Exatas e Tecnologia"))
                .findFirst().orElseThrow().getId();
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
                  "descricao": "Curso superior de tecnologia em sistemas",
                  "campusId": %d,
                  "areaId": %d
                }
                """.formatted(nome, acronimo, campusId, areaId);
    }

    @Test
    void deveCadastrarCursoEPersistirNoBanco() throws Exception {
        // WHEN + THEN (resposta HTTP)
        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(montarJson(NOME_NOVO, ACRONIMO_NOVO, campusId, areaId)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value(NOME_NOVO))
                .andExpect(jsonPath("$.acronimo").value(ACRONIMO_NOVO))
                .andExpect(jsonPath("$.campusNome").value("Campus Nova Cruz"))
                .andExpect(jsonPath("$.areaNome").value("Exatas e Tecnologia"));

        // THEN (banco): o curso realmente foi gravado
        assertThat(cursoRepository.existsByCampusIdAndNomeIgnoreCase(campusId, NOME_NOVO)).isTrue();
    }

    @Test
    void deveRetornarConflitoAoCadastrarCursoComNomeRepetidoNoCampus() throws Exception {
        // WHEN + THEN: "Análise e Desenvolvimento de Sistemas" já existe no IFNC (seed)
        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(montarJson("Análise e Desenvolvimento de Sistemas", ACRONIMO_NOVO, campusId, areaId)))
                .andExpect(status().isConflict());
    }

    @Test
    void deveRetornarConflitoAoCadastrarCursoComAcronimoRepetidoNoCampus() throws Exception {
        // WHEN + THEN: "TADS" já existe no IFNC (seed)
        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(montarJson(NOME_NOVO, "TADS", campusId, areaId)))
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
        // nome em branco viola o @NotBlank do DTO, e o service nem é chamado
        long totalAntes = cursoRepository.count();

        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(montarJson("", ACRONIMO_NOVO, campusId, areaId)))
                .andExpect(status().isBadRequest());

        assertThat(cursoRepository.count()).isEqualTo(totalAntes);
    }
}
