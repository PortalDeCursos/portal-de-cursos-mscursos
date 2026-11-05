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
}
