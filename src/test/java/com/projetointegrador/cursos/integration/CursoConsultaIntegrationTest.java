package com.projetointegrador.cursos.integration;

import com.projetointegrador.cursos.infrastructure.repository.AreaConhecimentoRepository;
import com.projetointegrador.cursos.infrastructure.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CursoConsultaIntegrationTest {

    private static final String CURSOS = "/cursos";
    private static final String BUSCAR = "/cursos/buscar";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AreaConhecimentoRepository areaConhecimentoRepository;

    private Long cursoTadsId;
    private Long areaExatasId;

    @BeforeEach
    void setUp() {
        // GIVEN (comum): ids dos dados que já vêm da migration de seed
        cursoTadsId = cursoRepository.findAll().stream()
                .filter(c -> c.getAcronimo().equals("TADS"))
                .findFirst().orElseThrow().getId();

        areaExatasId = areaConhecimentoRepository.findAll().stream()
                .filter(a -> a.getNome().equals("Exatas e Tecnologia"))
                .findFirst().orElseThrow().getId();
    }

    @Test
    void deveListarCursosComPaginacao() throws Exception {
        // WHEN + THEN: o seed tem 3 cursos, então size=2 devolve só 2
        mockMvc.perform(get(CURSOS)
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    void deveListarTodosOsCursosDoSeed() throws Exception {
        mockMvc.perform(get(CURSOS)
                        .param("page", "0")
                        .param("size", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].acronimo", hasItems("TADS", "TPQ", "TADM")));
    }

    @Test
    void deveBuscarCursoPorIdComSucesso() throws Exception {
        mockMvc.perform(get(CURSOS + "/{id}", cursoTadsId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cursoTadsId))
                .andExpect(jsonPath("$.nome").value("Análise e Desenvolvimento de Sistemas"))
                .andExpect(jsonPath("$.acronimo").value("TADS"))
                .andExpect(jsonPath("$.campusNome").value("Campus Nova Cruz"))
                .andExpect(jsonPath("$.areaNome").value("Exatas e Tecnologia"));
    }

    @Test
    void deveRetornarNaoEncontradoAoBuscarCursoComIdInexistente() throws Exception {
        mockMvc.perform(get(CURSOS + "/{id}", 999999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveFiltrarCursosPorGrau() throws Exception {
        // SUBSEQUENTE: só o TADM
        mockMvc.perform(get(BUSCAR)
                        .param("grau", "SUBSEQUENTE")
                        .param("size", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].acronimo", hasItem("TADM")))
                .andExpect(jsonPath("$.content[*].acronimo", not(hasItem("TADS"))));
    }

    @Test
    void deveFiltrarCursosPorTurno() throws Exception {
        // VESPERTINO: só o TPQ
        mockMvc.perform(get(BUSCAR)
                        .param("turno", "VESPERTINO")
                        .param("size", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].acronimo", hasItem("TPQ")))
                .andExpect(jsonPath("$.content[*].acronimo", not(hasItem("TADS"))));
    }

    @Test
    void deveFiltrarCursosPorModalidade() throws Exception {
        mockMvc.perform(get(BUSCAR)
                        .param("modalidade", "PRESENCIAL")
                        .param("size", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].acronimo", hasItems("TADS", "TPQ", "TADM")));
    }

    @Test
    void deveFiltrarCursosPorArea() throws Exception {
        // Exatas e Tecnologia: só o TADS
        mockMvc.perform(get(BUSCAR)
                        .param("areaId", String.valueOf(areaExatasId))
                        .param("size", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].acronimo", hasItem("TADS")))
                .andExpect(jsonPath("$.content[*].acronimo", not(hasItem("TPQ"))));
    }

    @Test
    void deveRetornarRequisicaoInvalidaAoFiltrarComGrauInexistente() throws Exception {
        mockMvc.perform(get(BUSCAR)
                        .param("grau", "GRAU_INEXISTENTE"))
                .andExpect(status().isBadRequest());
    }
}
