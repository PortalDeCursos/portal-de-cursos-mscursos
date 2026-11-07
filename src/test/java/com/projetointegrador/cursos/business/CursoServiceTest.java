package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.CursoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CursoResponseDTO;
import com.projetointegrador.cursos.business.mapper.CursoMapper;
import com.projetointegrador.cursos.infrastructure.entity.AreaConhecimento;
import com.projetointegrador.cursos.infrastructure.entity.Campus;
import com.projetointegrador.cursos.infrastructure.entity.Curso;
import com.projetointegrador.cursos.infrastructure.enums.GrauEnum;
import com.projetointegrador.cursos.infrastructure.enums.ModalidadeEnum;
import com.projetointegrador.cursos.infrastructure.enums.TurnoEnum;
import com.projetointegrador.cursos.infrastructure.exception.ConflictException;
import com.projetointegrador.cursos.infrastructure.exception.ResourceNotFoundException;
import com.projetointegrador.cursos.infrastructure.repository.AreaConhecimentoRepository;
import com.projetointegrador.cursos.infrastructure.repository.CampusRepository;
import com.projetointegrador.cursos.infrastructure.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private CursoMapper cursoMapper;

    @Mock
    private CampusRepository campusRepository;

    @Mock
    private AreaConhecimentoRepository areaConhecimentoRepository;

    @Mock
    private Campus campus;

    @Mock
    private AreaConhecimento area;

    @Mock
    private Curso curso;

    @InjectMocks
    private CursoService cursoService;

    private CursoRequestDTO requestDTO;
    private CursoResponseDTO responseDTO;
    private Pageable pageable;

    @BeforeEach
    void setUp() {

        LocalDateTime agora = LocalDateTime.now();

        requestDTO = new CursoRequestDTO(
                "Análise e Desenvolvimento de Sistemas",
                "TADS",
                GrauEnum.TECNOLOGO,
                "6 semestres",
                TurnoEnum.NOTURNO,
                ModalidadeEnum.PRESENCIAL,
                2000,
                30,
                5,
                "Curso superior de tecnologia em sistemas",
                1L,
                2L);

        responseDTO = new CursoResponseDTO(
                10L,
                1L,
                "Campus Nova Cruz",
                2L,
                "Informática",
                "Análise e Desenvolvimento de Sistemas",
                "TADS",
                GrauEnum.TECNOLOGO,
                "6 semestres",
                TurnoEnum.NOTURNO,
                ModalidadeEnum.PRESENCIAL,
                2000,
                30,
                5,
                "Curso superior de tecnologia em sistemas",
                agora,
                agora);

        pageable = PageRequest.of(0, 10);
    }

    @Test
    void deveCadastrarCursoComSucesso() {
        // GIVEN
        when(campusRepository.findById(1L)).thenReturn(Optional.of(campus));
        when(areaConhecimentoRepository.findById(2L)).thenReturn(Optional.of(area));
        when(campus.getId()).thenReturn(1L);
        when(cursoRepository.existsByCampusIdAndNomeIgnoreCase(1L, requestDTO.nome())).thenReturn(false);
        when(cursoRepository.existsByCampusIdAndAcronimoIgnoreCase(1L, requestDTO.acronimo())).thenReturn(false);
        when(cursoMapper.toEntity(requestDTO)).thenReturn(curso);
        when(cursoRepository.save(curso)).thenReturn(curso);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(responseDTO);

        // WHEN
        CursoResponseDTO resultado = cursoService.cadastrar(requestDTO);

        // THEN
        assertEquals(responseDTO, resultado);

        verify(campusRepository).findById(1L);
        verify(areaConhecimentoRepository).findById(2L);
        verify(cursoRepository).existsByCampusIdAndNomeIgnoreCase(1L, requestDTO.nome());
        verify(cursoRepository).existsByCampusIdAndAcronimoIgnoreCase(1L, requestDTO.acronimo());
        verify(cursoMapper).toEntity(requestDTO);
        verify(curso).setCampus(campus);
        verify(curso).setAreaConhecimento(area);
        verify(cursoRepository).save(curso);
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(cursoRepository, cursoMapper, campusRepository, areaConhecimentoRepository);
    }

    @Test
    void deveLancarExcecaoAoCadastrarQuandoCampusNaoExistir() {
        // GIVEN
        when(campusRepository.findById(1L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> cursoService.cadastrar(requestDTO));

        assertThat(excecao.getMessage()).contains("1");

        verify(campusRepository).findById(1L);
        verifyNoInteractions(areaConhecimentoRepository, cursoRepository, cursoMapper);
        verifyNoMoreInteractions(campusRepository);
    }

    @Test
    void deveLancarExcecaoAoCadastrarQuandoAreaNaoExistir() {
        // GIVEN
        when(campusRepository.findById(1L)).thenReturn(Optional.of(campus));
        when(areaConhecimentoRepository.findById(2L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> cursoService.cadastrar(requestDTO));

        assertThat(excecao.getMessage()).contains("2");

        verify(campusRepository).findById(1L);
        verify(areaConhecimentoRepository).findById(2L);
        verifyNoInteractions(cursoRepository, cursoMapper);
        verifyNoMoreInteractions(campusRepository, areaConhecimentoRepository);
    }

    @Test
    void deveLancarConflitoAoCadastrarQuandoNomeJaExistirNoCampus() {
        // GIVEN
        when(campusRepository.findById(1L)).thenReturn(Optional.of(campus));
        when(areaConhecimentoRepository.findById(2L)).thenReturn(Optional.of(area));
        when(campus.getId()).thenReturn(1L);
        when(cursoRepository.existsByCampusIdAndNomeIgnoreCase(1L, requestDTO.nome())).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> cursoService.cadastrar(requestDTO));

        assertThat(excecao.getMessage()).contains(requestDTO.nome());

        verify(cursoRepository).existsByCampusIdAndNomeIgnoreCase(1L, requestDTO.nome());
        verify(cursoRepository, never()).existsByCampusIdAndAcronimoIgnoreCase(any(), any());
        verify(cursoRepository, never()).save(any());
        verifyNoInteractions(cursoMapper);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void deveLancarConflitoAoCadastrarQuandoAcronimoJaExistirNoCampus() {
        // GIVEN
        when(campusRepository.findById(1L)).thenReturn(Optional.of(campus));
        when(areaConhecimentoRepository.findById(2L)).thenReturn(Optional.of(area));
        when(campus.getId()).thenReturn(1L);
        when(cursoRepository.existsByCampusIdAndNomeIgnoreCase(1L, requestDTO.nome())).thenReturn(false);
        when(cursoRepository.existsByCampusIdAndAcronimoIgnoreCase(1L, requestDTO.acronimo())).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> cursoService.cadastrar(requestDTO));

        assertThat(excecao.getMessage()).contains(requestDTO.acronimo());

        verify(cursoRepository).existsByCampusIdAndNomeIgnoreCase(1L, requestDTO.nome());
        verify(cursoRepository).existsByCampusIdAndAcronimoIgnoreCase(1L, requestDTO.acronimo());
        verify(cursoRepository, never()).save(any());
        verifyNoInteractions(cursoMapper);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void deveBuscarCursoPorIdComSucesso() {
        // GIVEN
        when(cursoRepository.findById(10L)).thenReturn(Optional.of(curso));
        when(cursoMapper.toResponseDTO(curso)).thenReturn(responseDTO);

        // WHEN
        CursoResponseDTO resultado = cursoService.buscarPorId(10L);

        // THEN
        assertEquals(responseDTO, resultado);

        verify(cursoRepository).findById(10L);
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(cursoRepository, cursoMapper);
    }

    @Test
    void deveLancarExcecaoQuandoCursoNaoForEncontradoPorId() {
        // GIVEN
        when(cursoRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> cursoService.buscarPorId(99L));

        assertThat(excecao.getMessage()).contains("99");

        verify(cursoRepository).findById(99L);
        verifyNoInteractions(cursoMapper);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void deveListarCursosPaginados() {
        // GIVEN
        Page<Curso> pagina = new PageImpl<>(List.of(curso), pageable, 1);

        when(cursoRepository.findAll(pageable)).thenReturn(pagina);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(responseDTO);

        // WHEN
        Page<CursoResponseDTO> resultado = cursoService.listar(pageable);

        // THEN
        assertThat(resultado.getContent()).containsExactly(responseDTO);
        assertThat(resultado.getTotalElements()).isEqualTo(1);

        verify(cursoRepository).findAll(pageable);
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(cursoRepository, cursoMapper);
    }

    @Test
    void deveRetornarPaginaVaziaQuandoNaoHouverCursos() {
        // GIVEN
        Page<Curso> paginaVazia = new PageImpl<>(List.of(), pageable, 0);

        when(cursoRepository.findAll(pageable)).thenReturn(paginaVazia);

        // WHEN
        Page<CursoResponseDTO> resultado = cursoService.listar(pageable);

        // THEN
        assertThat(resultado.getContent()).isEmpty();

        verify(cursoRepository).findAll(pageable);
        verifyNoInteractions(cursoMapper);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void deveBuscarCursosPorGrau() {
        // GIVEN
        Page<Curso> pagina = new PageImpl<>(List.of(curso), pageable, 1);

        when(cursoRepository.findByGrau(GrauEnum.TECNOLOGO, pageable)).thenReturn(pagina);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(responseDTO);

        // WHEN
        Page<CursoResponseDTO> resultado = cursoService.buscar(GrauEnum.TECNOLOGO, null, null, null, pageable);

        // THEN
        assertThat(resultado.getContent()).containsExactly(responseDTO);

        verify(cursoRepository).findByGrau(GrauEnum.TECNOLOGO, pageable);
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(cursoRepository, cursoMapper);
    }

    @Test
    void deveBuscarCursosPorTurno() {
        // GIVEN
        Page<Curso> pagina = new PageImpl<>(List.of(curso), pageable, 1);

        when(cursoRepository.findByTurno(TurnoEnum.NOTURNO, pageable)).thenReturn(pagina);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(responseDTO);

        // WHEN
        Page<CursoResponseDTO> resultado = cursoService.buscar(null, TurnoEnum.NOTURNO, null, null, pageable);

        // THEN
        assertThat(resultado.getContent()).containsExactly(responseDTO);

        verify(cursoRepository).findByTurno(TurnoEnum.NOTURNO, pageable);
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(cursoRepository, cursoMapper);
    }

    @Test
    void deveBuscarCursosPorModalidade() {
        // GIVEN
        Page<Curso> pagina = new PageImpl<>(List.of(curso), pageable, 1);

        when(cursoRepository.findByModalidade(ModalidadeEnum.PRESENCIAL, pageable)).thenReturn(pagina);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(responseDTO);

        // WHEN
        Page<CursoResponseDTO> resultado = cursoService.buscar(null, null, ModalidadeEnum.PRESENCIAL, null, pageable);

        // THEN
        assertThat(resultado.getContent()).containsExactly(responseDTO);

        verify(cursoRepository).findByModalidade(ModalidadeEnum.PRESENCIAL, pageable);
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(cursoRepository, cursoMapper);
    }

    @Test
    void deveBuscarCursosPorArea() {
        // GIVEN
        Page<Curso> pagina = new PageImpl<>(List.of(curso), pageable, 1);

        when(cursoRepository.findByAreaConhecimentoId(2L, pageable)).thenReturn(pagina);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(responseDTO);

        // WHEN
        Page<CursoResponseDTO> resultado = cursoService.buscar(null, null, null, 2L, pageable);

        // THEN
        assertThat(resultado.getContent()).containsExactly(responseDTO);

        verify(cursoRepository).findByAreaConhecimentoId(2L, pageable);
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(cursoRepository, cursoMapper);
    }

    @Test
    void deveListarTodosQuandoNenhumFiltroForInformado() {
        // GIVEN
        Page<Curso> pagina = new PageImpl<>(List.of(curso), pageable, 1);

        when(cursoRepository.findAll(pageable)).thenReturn(pagina);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(responseDTO);

        // WHEN
        Page<CursoResponseDTO> resultado = cursoService.buscar(null, null, null, null, pageable);

        // THEN
        assertThat(resultado.getContent()).containsExactly(responseDTO);

        verify(cursoRepository).findAll(pageable);
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(cursoRepository, cursoMapper);
    }

    @Test
    void devePriorizarFiltroDeGrauQuandoVariosFiltrosForemInformados() {
        // GIVEN
        Page<Curso> pagina = new PageImpl<>(List.of(curso), pageable, 1);

        when(cursoRepository.findByGrau(GrauEnum.TECNOLOGO, pageable)).thenReturn(pagina);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(responseDTO);

        // WHEN
        Page<CursoResponseDTO> resultado = cursoService.buscar(
                GrauEnum.TECNOLOGO, TurnoEnum.NOTURNO, ModalidadeEnum.PRESENCIAL, 2L, pageable);

        // THEN
        assertThat(resultado.getContent()).containsExactly(responseDTO);

        verify(cursoRepository).findByGrau(GrauEnum.TECNOLOGO, pageable);
        verify(cursoRepository, never()).findByTurno(any(), any());
        verify(cursoRepository, never()).findByModalidade(any(), any());
        verify(cursoRepository, never()).findByAreaConhecimentoId(any(), any());
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(cursoRepository, cursoMapper);
    }

    @Test
    void deveAtualizarCursoComSucesso() {
        // GIVEN
        when(cursoRepository.findById(10L)).thenReturn(Optional.of(curso));
        when(areaConhecimentoRepository.findById(2L)).thenReturn(Optional.of(area));
        when(curso.getCampus()).thenReturn(campus);
        when(campus.getId()).thenReturn(1L);
        when(cursoRepository.existsByCampusIdAndNomeIgnoreCaseAndIdNot(1L, requestDTO.nome(), 10L)).thenReturn(false);
        when(cursoRepository.existsByCampusIdAndAcronimoIgnoreCaseAndIdNot(1L, requestDTO.acronimo(), 10L)).thenReturn(false);
        doNothing().when(cursoMapper).updateEntityFromDTO(requestDTO, curso);
        when(cursoRepository.saveAndFlush(curso)).thenReturn(curso);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(responseDTO);

        // WHEN
        CursoResponseDTO resultado = cursoService.atualizar(10L, requestDTO);

        // THEN
        assertEquals(responseDTO, resultado);

        verify(cursoRepository).findById(10L);
        verify(areaConhecimentoRepository).findById(2L);
        verify(cursoRepository).existsByCampusIdAndNomeIgnoreCaseAndIdNot(1L, requestDTO.nome(), 10L);
        verify(cursoRepository).existsByCampusIdAndAcronimoIgnoreCaseAndIdNot(1L, requestDTO.acronimo(), 10L);
        verify(cursoMapper).updateEntityFromDTO(requestDTO, curso);
        verify(curso).setAreaConhecimento(area);
        verify(cursoRepository).saveAndFlush(curso);
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(cursoRepository, cursoMapper, areaConhecimentoRepository);
    }

    @Test
    void deveLancarExcecaoAoAtualizarQuandoCursoNaoExistir() {
        // GIVEN
        when(cursoRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> cursoService.atualizar(99L, requestDTO));

        assertThat(excecao.getMessage()).contains("99");

        verify(cursoRepository).findById(99L);
        verify(cursoRepository, never()).saveAndFlush(any());
        verifyNoInteractions(areaConhecimentoRepository, cursoMapper);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void deveLancarExcecaoAoAtualizarQuandoAreaNaoExistir() {
        // GIVEN
        when(cursoRepository.findById(10L)).thenReturn(Optional.of(curso));
        when(areaConhecimentoRepository.findById(2L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> cursoService.atualizar(10L, requestDTO));

        assertThat(excecao.getMessage()).contains("2");

        verify(cursoRepository).findById(10L);
        verify(areaConhecimentoRepository).findById(2L);
        verify(cursoRepository, never()).saveAndFlush(any());
        verifyNoInteractions(cursoMapper);
        verifyNoMoreInteractions(cursoRepository, areaConhecimentoRepository);
    }

    @Test
    void deveLancarConflitoAoAtualizarQuandoNomeJaEstiverEmUsoNoCampus() {
        // GIVEN
        when(cursoRepository.findById(10L)).thenReturn(Optional.of(curso));
        when(areaConhecimentoRepository.findById(2L)).thenReturn(Optional.of(area));
        when(curso.getCampus()).thenReturn(campus);
        when(campus.getId()).thenReturn(1L);
        when(cursoRepository.existsByCampusIdAndNomeIgnoreCaseAndIdNot(1L, requestDTO.nome(), 10L)).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> cursoService.atualizar(10L, requestDTO));

        assertThat(excecao.getMessage()).contains(requestDTO.nome());

        verify(cursoRepository).existsByCampusIdAndNomeIgnoreCaseAndIdNot(1L, requestDTO.nome(), 10L);
        verify(cursoRepository, never()).existsByCampusIdAndAcronimoIgnoreCaseAndIdNot(any(), any(), any());
        verify(cursoRepository, never()).saveAndFlush(any());
        verifyNoInteractions(cursoMapper);
    }

    @Test
    void deveLancarConflitoAoAtualizarQuandoAcronimoJaEstiverEmUsoNoCampus() {
        // GIVEN
        when(cursoRepository.findById(10L)).thenReturn(Optional.of(curso));
        when(areaConhecimentoRepository.findById(2L)).thenReturn(Optional.of(area));
        when(curso.getCampus()).thenReturn(campus);
        when(campus.getId()).thenReturn(1L);
        when(cursoRepository.existsByCampusIdAndNomeIgnoreCaseAndIdNot(1L, requestDTO.nome(), 10L)).thenReturn(false);
        when(cursoRepository.existsByCampusIdAndAcronimoIgnoreCaseAndIdNot(1L, requestDTO.acronimo(), 10L)).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> cursoService.atualizar(10L, requestDTO));

        assertThat(excecao.getMessage()).contains(requestDTO.acronimo());

        verify(cursoRepository).existsByCampusIdAndNomeIgnoreCaseAndIdNot(1L, requestDTO.nome(), 10L);
        verify(cursoRepository).existsByCampusIdAndAcronimoIgnoreCaseAndIdNot(1L, requestDTO.acronimo(), 10L);
        verify(cursoRepository, never()).saveAndFlush(any());
        verifyNoInteractions(cursoMapper);
    }

    @Test
    void deveDeletarCursoComSucesso() {
        // GIVEN
        when(cursoRepository.findById(10L)).thenReturn(Optional.of(curso));
        doNothing().when(cursoRepository).delete(curso);

        // WHEN
        cursoService.deletar(10L);

        // THEN
        verify(cursoRepository).findById(10L);
        verify(cursoRepository).delete(curso);
        verifyNoMoreInteractions(cursoRepository);
    }

    @Test
    void deveLancarExcecaoAoDeletarQuandoCursoNaoExistir() {
        // GIVEN
        when(cursoRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> cursoService.deletar(99L));

        assertThat(excecao.getMessage()).contains("99");

        verify(cursoRepository).findById(99L);
        verify(cursoRepository, never()).delete(any());
        verifyNoMoreInteractions(cursoRepository);
    }
}
