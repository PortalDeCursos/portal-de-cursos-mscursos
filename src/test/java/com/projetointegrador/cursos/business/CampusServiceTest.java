package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.CampusRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CampusResponseDTO;
import com.projetointegrador.cursos.business.dto.response.CursoResponseDTO;
import com.projetointegrador.cursos.business.mapper.CampusMapper;
import com.projetointegrador.cursos.business.mapper.CursoMapper;
import com.projetointegrador.cursos.infrastructure.entity.Campus;
import com.projetointegrador.cursos.infrastructure.entity.Curso;
import com.projetointegrador.cursos.infrastructure.exception.ConflictException;
import com.projetointegrador.cursos.infrastructure.exception.ResourceNotFoundException;
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
public class CampusServiceTest {

    @Mock
    private CampusRepository campusRepository;

    @Mock
    private CampusMapper campusMapper;

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private CursoMapper cursoMapper;

    @InjectMocks
    private CampusService campusService;

    private CampusRequestDTO requestDTO;
    private Campus campus;
    private CampusResponseDTO responseDTO;

    @BeforeEach
    void setUp() {

        LocalDateTime agora = LocalDateTime.now();

        requestDTO = new CampusRequestDTO("Campus Nova Cruz", "IFNC", "Nova Cruz", "Rua Exemplo, 100");

        campus = new Campus();

        responseDTO = new CampusResponseDTO(1L, "Campus Nova Cruz", "IFNC", "Nova Cruz", "Rua Exemplo, 100", agora, agora);
    }

    @Test
    void deveCadastrarCampusComSucesso() {
        // GIVEN
        when(campusRepository.existsByNome("Campus Nova Cruz")).thenReturn(false);
        when(campusRepository.existsByAcronimo("IFNC")).thenReturn(false);
        when(campusMapper.toEntity(requestDTO)).thenReturn(campus);
        when(campusRepository.save(campus)).thenReturn(campus);
        when(campusMapper.toResponseDTO(campus)).thenReturn(responseDTO);

        // WHEN
        CampusResponseDTO resultado = campusService.cadastrar(requestDTO);

        // THEN
        assertEquals(responseDTO, resultado);
        assertThat(resultado.nome()).isEqualTo("Campus Nova Cruz");

        verify(campusRepository).existsByNome("Campus Nova Cruz");
        verify(campusRepository).existsByAcronimo("IFNC");
        verify(campusMapper).toEntity(requestDTO);
        verify(campusRepository).save(campus);
        verify(campusMapper).toResponseDTO(campus);
        verifyNoMoreInteractions(campusRepository, campusMapper);
    }

    @Test
    void deveLancarConflitoQuandoNomeJaExistir() {
        // GIVEN
        when(campusRepository.existsByNome("Campus Nova Cruz")).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> campusService.cadastrar(requestDTO));

        assertThat(excecao.getMessage()).contains("Campus Nova Cruz");

        verify(campusRepository).existsByNome("Campus Nova Cruz");
        verify(campusRepository, never()).save(any());
        verifyNoMoreInteractions(campusRepository, campusMapper);
    }

    @Test
    void deveLancarConflitoQuandoAcronimoJaExistir() {
        // GIVEN
        when(campusRepository.existsByNome("Campus Nova Cruz")).thenReturn(false);
        when(campusRepository.existsByAcronimo("IFNC")).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> campusService.cadastrar(requestDTO));

        assertThat(excecao.getMessage()).contains("IFNC");

        verify(campusRepository).existsByNome("Campus Nova Cruz");
        verify(campusRepository).existsByAcronimo("IFNC");
        verify(campusRepository, never()).save(any());
        verifyNoMoreInteractions(campusRepository, campusMapper);
    }

    @Test
    void deveBuscarCampusPorNomeComSucesso() {
        // GIVEN
        when(campusRepository.findByNomeContainingIgnoreCase("Nova")).thenReturn(List.of(campus));
        when(campusMapper.toResponseDTO(campus)).thenReturn(responseDTO);

        // WHEN
        List<CampusResponseDTO> resultado = campusService.buscarPorNome("Nova");

        // THEN
        assertThat(resultado).hasSize(1);
        assertThat(resultado).containsExactly(responseDTO);

        verify(campusRepository).findByNomeContainingIgnoreCase("Nova");
        verify(campusMapper).toResponseDTO(campus);
        verifyNoMoreInteractions(campusRepository, campusMapper);
    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumCampusForEncontradoPorNome() {
        // GIVEN
        when(campusRepository.findByNomeContainingIgnoreCase("Inexistente")).thenReturn(List.of());

        // WHEN
        List<CampusResponseDTO> resultado = campusService.buscarPorNome("Inexistente");

        // THEN
        assertThat(resultado).isEmpty();

        verify(campusRepository).findByNomeContainingIgnoreCase("Inexistente");
        verifyNoInteractions(campusMapper);
        verifyNoMoreInteractions(campusRepository);
    }

    @Test
    void deveBuscarCampusPorIdComSucesso() {
        // GIVEN
        when(campusRepository.findById(1L)).thenReturn(Optional.of(campus));
        when(campusMapper.toResponseDTO(campus)).thenReturn(responseDTO);

        // WHEN
        CampusResponseDTO resultado = campusService.buscarPorId(1L);

        // THEN
        assertEquals(responseDTO, resultado);

        verify(campusRepository).findById(1L);
        verify(campusMapper).toResponseDTO(campus);
        verifyNoMoreInteractions(campusRepository, campusMapper);
    }

    @Test
    void deveLancarExcecaoQuandoCampusNaoForEncontradoPorId() {
        // GIVEN
        when(campusRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> campusService.buscarPorId(99L));

        assertThat(excecao.getMessage()).contains("99");

        verify(campusRepository).findById(99L);
        verifyNoInteractions(campusMapper);
        verifyNoMoreInteractions(campusRepository);
    }

    @Test
    void deveListarTodosOsCampus() {
        // GIVEN
        Campus campus2 = mock(Campus.class);
        CampusResponseDTO responseDTO2 = new CampusResponseDTO(
                2L, "Campus Natal Central", "CNAT", "Natal", "Av. Exemplo, 200",
                LocalDateTime.now(), LocalDateTime.now());

        when(campusRepository.findAllByOrderByNomeAsc()).thenReturn(List.of(campus, campus2));
        when(campusMapper.toResponseDTO(campus)).thenReturn(responseDTO);
        when(campusMapper.toResponseDTO(campus2)).thenReturn(responseDTO2);

        // WHEN
        List<CampusResponseDTO> resultado = campusService.listar();

        // THEN
        assertThat(resultado).hasSize(2);
        assertThat(resultado).containsExactly(responseDTO, responseDTO2);

        verify(campusRepository).findAllByOrderByNomeAsc();
        verify(campusMapper).toResponseDTO(campus);
        verify(campusMapper).toResponseDTO(campus2);
        verifyNoMoreInteractions(campusRepository, campusMapper);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverCampus() {
        // GIVEN
        when(campusRepository.findAllByOrderByNomeAsc()).thenReturn(List.of());

        // WHEN
        List<CampusResponseDTO> resultado = campusService.listar();

        // THEN
        assertThat(resultado).isEmpty();

        verify(campusRepository).findAllByOrderByNomeAsc();
        verifyNoInteractions(campusMapper);
        verifyNoMoreInteractions(campusRepository);
    }

    @Test
    void deveFiltrarCampusPorCidadeComSucesso() {
        // GIVEN
        when(campusRepository.findByCidadeIgnoreCase("Nova Cruz")).thenReturn(List.of(campus));
        when(campusMapper.toResponseDTO(campus)).thenReturn(responseDTO);

        // WHEN
        List<CampusResponseDTO> resultado = campusService.filtrar("Nova Cruz");

        // THEN
        assertThat(resultado).hasSize(1);
        assertThat(resultado).containsExactly(responseDTO);

        verify(campusRepository).findByCidadeIgnoreCase("Nova Cruz");
        verify(campusMapper).toResponseDTO(campus);
        verifyNoMoreInteractions(campusRepository, campusMapper);
    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumCampusForEncontradoNaCidade() {
        // GIVEN
        when(campusRepository.findByCidadeIgnoreCase("Cidade Fantasma")).thenReturn(List.of());

        // WHEN
        List<CampusResponseDTO> resultado = campusService.filtrar("Cidade Fantasma");

        // THEN
        assertThat(resultado).isEmpty();

        verify(campusRepository).findByCidadeIgnoreCase("Cidade Fantasma");
        verifyNoInteractions(campusMapper);
        verifyNoMoreInteractions(campusRepository);
    }

    @Test
    void deveListarCursosDoCampusComSucesso() {
        // GIVEN
        Pageable pageable = PageRequest.of(0, 10);
        Curso curso = mock(Curso.class);
        CursoResponseDTO cursoDTO = mock(CursoResponseDTO.class);
        Page<Curso> paginaDeCursos = new PageImpl<>(List.of(curso), pageable, 1);

        when(campusRepository.existsById(1L)).thenReturn(true);
        when(cursoRepository.findByCampusId(1L, pageable)).thenReturn(paginaDeCursos);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(cursoDTO);

        // WHEN
        Page<CursoResponseDTO> resultado = campusService.listarCursos(1L, pageable);

        // THEN
        assertThat(resultado.getContent()).containsExactly(cursoDTO);
        assertThat(resultado.getTotalElements()).isEqualTo(1);

        verify(campusRepository).existsById(1L);
        verify(cursoRepository).findByCampusId(1L, pageable);
        verify(cursoMapper).toResponseDTO(curso);
        verifyNoMoreInteractions(campusRepository, cursoRepository, cursoMapper);
    }

    @Test
    void deveLancarExcecaoAoListarCursosQuandoCampusNaoExistir() {
        // GIVEN
        Pageable pageable = PageRequest.of(0, 10);
        when(campusRepository.existsById(99L)).thenReturn(false);

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> campusService.listarCursos(99L, pageable));

        assertThat(excecao.getMessage()).contains("99");

        verify(campusRepository).existsById(99L);
        verifyNoInteractions(cursoRepository, cursoMapper);
        verifyNoMoreInteractions(campusRepository);
    }
}
