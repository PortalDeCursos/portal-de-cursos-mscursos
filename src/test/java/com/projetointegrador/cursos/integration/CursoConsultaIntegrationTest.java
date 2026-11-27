package com.projetointegrador.cursos.integration;

import com.projetointegrador.cursos.infrastructure.entity.Curso;
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

    private Curso tads;

    @BeforeEach
    void setUp() {
        // GIVEN (comum): o curso TADS do IFNC, que vem do seed
        tads = cursoRepository.findAll().stream()
                .filter(c -> c.getAcronimo().equals("TADS")
                        && c.getCampus().getAcronimo().equals("IFNC"))
                .findFirst().orElseThrow();
    }

    @Test
    void deveListarCursosComPaginacao() throws Exception {
        mockMvc.perform(get(CURSOS)
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    void deveListarCursosIncluindoOTads() throws Exception {
        mockMvc.perform(get(CURSOS)
                        .param("page", "0")
                        .param("size", "1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].acronimo", hasItem("TADS")));
    }

    @Test
    void deveBuscarCursoPorIdComSucesso() throws Exception {
        mockMvc.perform(get(CURSOS + "/{id}", tads.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(tads.getId()))
                .andExpect(jsonPath("$.nome").value(tads.getNome()))
                .andExpect(jsonPath("$.acronimo").value("TADS"))
                .andExpect(jsonPath("$.campusNome").value("Campus Nova Cruz"));
    }

    @Test
    void deveRetornarNaoEncontradoAoBuscarCursoComIdInexistente() throws Exception {
        mockMvc.perform(get(CURSOS + "/{id}", 999999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveFiltrarCursosPorGrau() throws Exception {
        mockMvc.perform(get(BUSCAR)
                        .param("grau", tads.getGrau().name())
                        .param("size", "1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].acronimo", hasItem("TADS")));
    }

    @Test
    void deveFiltrarCursosPorTurno() throws Exception {
        mockMvc.perform(get(BUSCAR)
                        .param("turno", tads.getTurno().name())
                        .param("size", "1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].acronimo", hasItem("TADS")));
    }

    @Test
    void deveFiltrarCursosPorModalidade() throws Exception {
        mockMvc.perform(get(BUSCAR)
                        .param("modalidade", tads.getModalidade().name())
                        .param("size", "1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].acronimo", hasItem("TADS")));
    }

    @Test
    void deveFiltrarCursosPorArea() throws Exception {
        mockMvc.perform(get(BUSCAR)
                        .param("areaId", String.valueOf(tads.getAreaConhecimento().getId()))
                        .param("size", "1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].acronimo", hasItem("TADS")));
    }

    @Test
    void deveRetornarRequisicaoInvalidaAoFiltrarComGrauInexistente() throws Exception {
        mockMvc.perform(get(BUSCAR)
                        .param("grau", "GRAU_INEXISTENTE"))
                .andExpect(status().isBadRequest());
    }
}
