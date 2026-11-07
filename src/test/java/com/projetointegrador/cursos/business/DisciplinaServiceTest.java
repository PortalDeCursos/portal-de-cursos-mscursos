package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.DisciplinaRequestDTO;
import com.projetointegrador.cursos.business.dto.response.DisciplinaResponseDTO;
import com.projetointegrador.cursos.business.mapper.DisciplinaMapper;
import com.projetointegrador.cursos.infrastructure.entity.Curso;
import com.projetointegrador.cursos.infrastructure.entity.Disciplina;
import com.projetointegrador.cursos.infrastructure.enums.SemestreEnum;
import com.projetointegrador.cursos.infrastructure.exception.ConflictException;
import com.projetointegrador.cursos.infrastructure.exception.ResourceNotFoundException;
import com.projetointegrador.cursos.infrastructure.repository.CursoRepository;
import com.projetointegrador.cursos.infrastructure.repository.DisciplinaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DisciplinaServiceTest {

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @Mock
    private DisciplinaMapper disciplinaMapper;

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private Curso curso;

    @Mock
    private Disciplina disciplina;

    @InjectMocks
    private DisciplinaService disciplinaService;

    private DisciplinaRequestDTO requestDTO;
    private DisciplinaResponseDTO responseDTO;

    @BeforeEach
    void setUp() {

        LocalDateTime agora = LocalDateTime.now();

        requestDTO = new DisciplinaRequestDTO(
                1L,
                "Programação Orientada a Objetos",
                "POO001",
                60,
                SemestreEnum.SEGUNDO,
                "Conceitos de orientação a objetos com Java",
                true);

        responseDTO = new DisciplinaResponseDTO(
                10L,
                1L,
                "Programação Orientada a Objetos",
                "POO001",
                60,
                SemestreEnum.SEGUNDO,
                "Conceitos de orientação a objetos com Java",
                true,
                agora,
                agora);
    }

    @Test
    void deveCadastrarDisciplinaComSucesso() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(disciplinaRepository.existsByNomeIgnoreCase(requestDTO.nome())).thenReturn(false);
        when(disciplinaRepository.existsByCodigoIgnoreCase(requestDTO.codigo())).thenReturn(false);
        when(disciplinaMapper.toEntity(requestDTO)).thenReturn(disciplina);
        when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);
        when(disciplinaMapper.toResponseDTO(disciplina)).thenReturn(responseDTO);

        // WHEN
        DisciplinaResponseDTO resultado = disciplinaService.cadastrar(requestDTO);

        // THEN
        assertEquals(responseDTO, resultado);

        verify(cursoRepository).findById(1L);
        verify(disciplinaRepository).existsByNomeIgnoreCase(requestDTO.nome());
        verify(disciplinaRepository).existsByCodigoIgnoreCase(requestDTO.codigo());
        verify(disciplinaMapper).toEntity(requestDTO);
        verify(disciplina).setCurso(curso);
        verify(disciplinaRepository).save(disciplina);
        verify(disciplinaMapper).toResponseDTO(disciplina);
        verifyNoMoreInteractions(disciplinaRepository, disciplinaMapper, cursoRepository);
    }

    @Test
    void deveLancarExcecaoAoCadastrarQuandoCursoNaoExistir() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> disciplinaService.cadastrar(requestDTO));

        assertThat(excecao.getMessage()).contains("1");

        verify(cursoRepository).findById(1L);
        verifyNoInteractions(disciplinaRepository, disciplinaMapper);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void deveLancarConflitoAoCadastrarQuandoNomeJaExistir() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(disciplinaRepository.existsByNomeIgnoreCase(requestDTO.nome())).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> disciplinaService.cadastrar(requestDTO));

        assertThat(excecao.getMessage()).contains(requestDTO.nome());

        verify(disciplinaRepository).existsByNomeIgnoreCase(requestDTO.nome());
        verify(disciplinaRepository, never()).existsByCodigoIgnoreCase(any());
        verify(disciplinaRepository, never()).save(any());
        verifyNoInteractions(disciplinaMapper);
        verifyNoMoreInteractions(disciplinaRepository);
    }

    @Test
    void deveLancarConflitoAoCadastrarQuandoCodigoJaExistir() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(disciplinaRepository.existsByNomeIgnoreCase(requestDTO.nome())).thenReturn(false);
        when(disciplinaRepository.existsByCodigoIgnoreCase(requestDTO.codigo())).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> disciplinaService.cadastrar(requestDTO));

        assertThat(excecao.getMessage()).contains(requestDTO.codigo());

        verify(disciplinaRepository).existsByNomeIgnoreCase(requestDTO.nome());
        verify(disciplinaRepository).existsByCodigoIgnoreCase(requestDTO.codigo());
        verify(disciplinaRepository, never()).save(any());
        verifyNoInteractions(disciplinaMapper);
        verifyNoMoreInteractions(disciplinaRepository);
    }

    @Test
    void deveBuscarDisciplinaPorIdComSucesso() {
        // GIVEN
        when(disciplinaRepository.findById(10L)).thenReturn(Optional.of(disciplina));
        when(disciplinaMapper.toResponseDTO(disciplina)).thenReturn(responseDTO);

        // WHEN
        DisciplinaResponseDTO resultado = disciplinaService.buscarPorId(10L);

        // THEN
        assertEquals(responseDTO, resultado);

        verify(disciplinaRepository).findById(10L);
        verify(disciplinaMapper).toResponseDTO(disciplina);
        verifyNoMoreInteractions(disciplinaRepository, disciplinaMapper);
    }

    @Test
    void deveLancarExcecaoQuandoDisciplinaNaoForEncontradaPorId() {
        // GIVEN
        when(disciplinaRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> disciplinaService.buscarPorId(99L));

        assertThat(excecao.getMessage()).contains("99");

        verify(disciplinaRepository).findById(99L);
        verifyNoInteractions(disciplinaMapper);
        verifyNoMoreInteractions(disciplinaRepository);
    }

    @Test
    void deveListarDisciplinasPorCursoComSucesso() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(disciplinaRepository.findByCursoId(1L)).thenReturn(List.of(disciplina));
        when(disciplinaMapper.toResponseDTO(disciplina)).thenReturn(responseDTO);

        // WHEN
        List<DisciplinaResponseDTO> resultado = disciplinaService.listarPorCurso(1L);

        // THEN
        assertThat(resultado).hasSize(1);
        assertThat(resultado).containsExactly(responseDTO);

        verify(cursoRepository).findById(1L);
        verify(disciplinaRepository).findByCursoId(1L);
        verify(disciplinaMapper).toResponseDTO(disciplina);
        verifyNoMoreInteractions(cursoRepository, disciplinaRepository, disciplinaMapper);
    }

    @Test
    void deveRetornarListaVaziaQuandoCursoNaoTiverDisciplinas() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(disciplinaRepository.findByCursoId(1L)).thenReturn(List.of());

        // WHEN
        List<DisciplinaResponseDTO> resultado = disciplinaService.listarPorCurso(1L);

        // THEN
        assertThat(resultado).isEmpty();

        verify(cursoRepository).findById(1L);
        verify(disciplinaRepository).findByCursoId(1L);
        verifyNoInteractions(disciplinaMapper);
        verifyNoMoreInteractions(cursoRepository, disciplinaRepository);
    }

    @Test
    void deveLancarExcecaoAoListarPorCursoQuandoCursoNaoExistir() {
        // GIVEN
        when(cursoRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> disciplinaService.listarPorCurso(99L));

        assertThat(excecao.getMessage()).contains("99");

        verify(cursoRepository).findById(99L);
        verifyNoInteractions(disciplinaRepository, disciplinaMapper);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void deveFiltrarDisciplinasPorSemestreEObrigatoriedade() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(disciplinaRepository.findByCursoIdAndSemestreAndObrigatoria(1L, SemestreEnum.SEGUNDO, true))
                .thenReturn(List.of(disciplina));
        when(disciplinaMapper.toResponseDTO(disciplina)).thenReturn(responseDTO);

        // WHEN
        List<DisciplinaResponseDTO> resultado = disciplinaService.filtrar(1L, SemestreEnum.SEGUNDO, true);

        // THEN
        assertThat(resultado).containsExactly(responseDTO);

        verify(cursoRepository).findById(1L);
        verify(disciplinaRepository).findByCursoIdAndSemestreAndObrigatoria(1L, SemestreEnum.SEGUNDO, true);
        verify(disciplinaMapper).toResponseDTO(disciplina);
        verifyNoMoreInteractions(cursoRepository, disciplinaRepository, disciplinaMapper);
    }

    @Test
    void deveFiltrarDisciplinasApenasPorSemestre() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(disciplinaRepository.findByCursoIdAndSemestre(1L, SemestreEnum.SEGUNDO))
                .thenReturn(List.of(disciplina));
        when(disciplinaMapper.toResponseDTO(disciplina)).thenReturn(responseDTO);

        // WHEN
        List<DisciplinaResponseDTO> resultado = disciplinaService.filtrar(1L, SemestreEnum.SEGUNDO, null);

        // THEN
        assertThat(resultado).containsExactly(responseDTO);

        verify(cursoRepository).findById(1L);
        verify(disciplinaRepository).findByCursoIdAndSemestre(1L, SemestreEnum.SEGUNDO);
        verify(disciplinaMapper).toResponseDTO(disciplina);
        verifyNoMoreInteractions(cursoRepository, disciplinaRepository, disciplinaMapper);
    }

    @Test
    void deveFiltrarDisciplinasApenasPorObrigatoriedade() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(disciplinaRepository.findByCursoIdAndObrigatoria(1L, true))
                .thenReturn(List.of(disciplina));
        when(disciplinaMapper.toResponseDTO(disciplina)).thenReturn(responseDTO);

        // WHEN
        List<DisciplinaResponseDTO> resultado = disciplinaService.filtrar(1L, null, true);

        // THEN
        assertThat(resultado).containsExactly(responseDTO);

        verify(cursoRepository).findById(1L);
        verify(disciplinaRepository).findByCursoIdAndObrigatoria(1L, true);
        verify(disciplinaMapper).toResponseDTO(disciplina);
        verifyNoMoreInteractions(cursoRepository, disciplinaRepository, disciplinaMapper);
    }

    @Test
    void deveListarTodasDoCursoQuandoNenhumFiltroForInformado() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(disciplinaRepository.findByCursoId(1L)).thenReturn(List.of(disciplina));
        when(disciplinaMapper.toResponseDTO(disciplina)).thenReturn(responseDTO);

        // WHEN
        List<DisciplinaResponseDTO> resultado = disciplinaService.filtrar(1L, null, null);

        // THEN
        assertThat(resultado).containsExactly(responseDTO);

        verify(cursoRepository).findById(1L);
        verify(disciplinaRepository).findByCursoId(1L);
        verify(disciplinaMapper).toResponseDTO(disciplina);
        verifyNoMoreInteractions(cursoRepository, disciplinaRepository, disciplinaMapper);
    }

    @Test
    void deveRetornarListaVaziaQuandoFiltroNaoEncontrarDisciplinas() {
        // GIVEN
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(disciplinaRepository.findByCursoIdAndSemestre(1L, SemestreEnum.DECIMO))
                .thenReturn(List.of());

        // WHEN
        List<DisciplinaResponseDTO> resultado = disciplinaService.filtrar(1L, SemestreEnum.DECIMO, null);

        // THEN
        assertThat(resultado).isEmpty();

        verify(cursoRepository).findById(1L);
        verify(disciplinaRepository).findByCursoIdAndSemestre(1L, SemestreEnum.DECIMO);
        verifyNoInteractions(disciplinaMapper);
        verifyNoMoreInteractions(cursoRepository, disciplinaRepository);
    }

    @Test
    void deveLancarExcecaoAoFiltrarQuandoCursoNaoExistir() {
        // GIVEN
        when(cursoRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> disciplinaService.filtrar(99L, SemestreEnum.SEGUNDO, true));

        assertThat(excecao.getMessage()).contains("99");

        verify(cursoRepository).findById(99L);
        verifyNoInteractions(disciplinaRepository, disciplinaMapper);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void deveAtualizarDisciplinaComSucesso() {
        // GIVEN
        when(disciplinaRepository.findById(10L)).thenReturn(Optional.of(disciplina));
        when(disciplinaRepository.existsByNomeIgnoreCaseAndIdNot(requestDTO.nome(), 10L)).thenReturn(false);
        when(disciplinaRepository.existsByCodigoIgnoreCaseAndIdNot(requestDTO.codigo(), 10L)).thenReturn(false);
        doNothing().when(disciplinaMapper).updateEntityFromDTO(requestDTO, disciplina);
        when(disciplinaRepository.saveAndFlush(disciplina)).thenReturn(disciplina);
        when(disciplinaMapper.toResponseDTO(disciplina)).thenReturn(responseDTO);

        // WHEN
        DisciplinaResponseDTO resultado = disciplinaService.atualizar(10L, requestDTO);

        // THEN
        assertEquals(responseDTO, resultado);

        verify(disciplinaRepository).findById(10L);
        verify(disciplinaRepository).existsByNomeIgnoreCaseAndIdNot(requestDTO.nome(), 10L);
        verify(disciplinaRepository).existsByCodigoIgnoreCaseAndIdNot(requestDTO.codigo(), 10L);
        verify(disciplinaMapper).updateEntityFromDTO(requestDTO, disciplina);
        verify(disciplinaRepository).saveAndFlush(disciplina);
        verify(disciplinaMapper).toResponseDTO(disciplina);
        verifyNoMoreInteractions(disciplinaRepository, disciplinaMapper);
    }

    @Test
    void deveLancarExcecaoAoAtualizarQuandoDisciplinaNaoExistir() {
        // GIVEN
        when(disciplinaRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> disciplinaService.atualizar(99L, requestDTO));

        assertThat(excecao.getMessage()).contains("99");

        verify(disciplinaRepository).findById(99L);
        verify(disciplinaRepository, never()).saveAndFlush(any());
        verifyNoInteractions(disciplinaMapper);
        verifyNoMoreInteractions(disciplinaRepository);
    }

    @Test
    void deveLancarConflitoAoAtualizarQuandoNomeJaEstiverEmUso() {
        // GIVEN
        when(disciplinaRepository.findById(10L)).thenReturn(Optional.of(disciplina));
        when(disciplinaRepository.existsByNomeIgnoreCaseAndIdNot(requestDTO.nome(), 10L)).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> disciplinaService.atualizar(10L, requestDTO));

        assertThat(excecao.getMessage()).contains(requestDTO.nome());

        verify(disciplinaRepository).findById(10L);
        verify(disciplinaRepository).existsByNomeIgnoreCaseAndIdNot(requestDTO.nome(), 10L);
        verify(disciplinaRepository, never()).existsByCodigoIgnoreCaseAndIdNot(any(), any());
        verify(disciplinaRepository, never()).saveAndFlush(any());
        verifyNoInteractions(disciplinaMapper);
        verifyNoMoreInteractions(disciplinaRepository);
    }

    @Test
    void deveLancarConflitoAoAtualizarQuandoCodigoJaEstiverEmUso() {
        // GIVEN
        when(disciplinaRepository.findById(10L)).thenReturn(Optional.of(disciplina));
        when(disciplinaRepository.existsByNomeIgnoreCaseAndIdNot(requestDTO.nome(), 10L)).thenReturn(false);
        when(disciplinaRepository.existsByCodigoIgnoreCaseAndIdNot(requestDTO.codigo(), 10L)).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> disciplinaService.atualizar(10L, requestDTO));

        assertThat(excecao.getMessage()).contains(requestDTO.codigo());

        verify(disciplinaRepository).findById(10L);
        verify(disciplinaRepository).existsByNomeIgnoreCaseAndIdNot(requestDTO.nome(), 10L);
        verify(disciplinaRepository).existsByCodigoIgnoreCaseAndIdNot(requestDTO.codigo(), 10L);
        verify(disciplinaRepository, never()).saveAndFlush(any());
        verifyNoInteractions(disciplinaMapper);
        verifyNoMoreInteractions(disciplinaRepository);
    }

    @Test
    void deveDeletarDisciplinaComSucesso() {
        // GIVEN
        when(disciplinaRepository.findById(10L)).thenReturn(Optional.of(disciplina));
        doNothing().when(disciplinaRepository).delete(disciplina);

        // WHEN
        disciplinaService.deletar(10L);

        // THEN
        verify(disciplinaRepository).findById(10L);
        verify(disciplinaRepository).delete(disciplina);
        verifyNoMoreInteractions(disciplinaRepository);
    }

    @Test
    void deveLancarExcecaoAoDeletarQuandoDisciplinaNaoExistir() {
        // GIVEN
        when(disciplinaRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> disciplinaService.deletar(99L));

        assertThat(excecao.getMessage()).contains("99");

        verify(disciplinaRepository).findById(99L);
        verify(disciplinaRepository, never()).delete(any());
        verifyNoMoreInteractions(disciplinaRepository);
    }
}
