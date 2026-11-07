package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.AreaConhecimentoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.AreaConhecimentoResponseDTO;
import com.projetointegrador.cursos.business.mapper.AreaConhecimentoMapper;
import com.projetointegrador.cursos.infrastructure.entity.AreaConhecimento;
import com.projetointegrador.cursos.infrastructure.exception.ConflictException;
import com.projetointegrador.cursos.infrastructure.exception.ResourceNotFoundException;
import com.projetointegrador.cursos.infrastructure.repository.AreaConhecimentoRepository;
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
public class AreaConhecimentoServiceTest {

    @Mock
    private AreaConhecimentoRepository areaConhecimentoRepository;

    @Mock
    private AreaConhecimentoMapper areaConhecimentoMapper;

    @InjectMocks
    private AreaConhecimentoService areaConhecimentoService;

    private AreaConhecimentoRequestDTO requestDTO;
    private AreaConhecimento areaConhecimento;
    private AreaConhecimentoResponseDTO responseDTO;

    @BeforeEach
    void setUp() {

        LocalDateTime agora = LocalDateTime.now();

        requestDTO = new AreaConhecimentoRequestDTO(
                "Informática",
                "Cursos voltados à tecnologia da informação");

        areaConhecimento = new AreaConhecimento();

        responseDTO = new AreaConhecimentoResponseDTO(
                1L,
                "Informática",
                "Cursos voltados à tecnologia da informação",
                agora,
                agora);
    }

    @Test
    void deveCadastrarAreaConhecimentoComSucesso() {
        // GIVEN
        when(areaConhecimentoRepository.existsByNome(requestDTO.nome())).thenReturn(false);
        when(areaConhecimentoMapper.toEntity(requestDTO)).thenReturn(areaConhecimento);
        when(areaConhecimentoRepository.save(areaConhecimento)).thenReturn(areaConhecimento);
        when(areaConhecimentoMapper.toResponseDTO(areaConhecimento)).thenReturn(responseDTO);

        // WHEN
        AreaConhecimentoResponseDTO resultado = areaConhecimentoService.cadastrar(requestDTO);

        // THEN
        assertEquals(responseDTO, resultado);
        assertThat(resultado.nome()).isEqualTo("Informática");

        verify(areaConhecimentoRepository).existsByNome(requestDTO.nome());
        verify(areaConhecimentoMapper).toEntity(requestDTO);
        verify(areaConhecimentoRepository).save(areaConhecimento);
        verify(areaConhecimentoMapper).toResponseDTO(areaConhecimento);
        verifyNoMoreInteractions(areaConhecimentoRepository, areaConhecimentoMapper);
    }

    @Test
    void deveLancarConflitoQuandoNomeJaExistir() {
        // GIVEN
        when(areaConhecimentoRepository.existsByNome(requestDTO.nome())).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> areaConhecimentoService.cadastrar(requestDTO));

        assertThat(excecao.getMessage()).contains(requestDTO.nome());

        verify(areaConhecimentoRepository).existsByNome(requestDTO.nome());
        verify(areaConhecimentoRepository, never()).save(any());
        verifyNoInteractions(areaConhecimentoMapper);
        verifyNoMoreInteractions(areaConhecimentoRepository);
    }

    @Test
    void deveBuscarAreaConhecimentoPorIdComSucesso() {
        // GIVEN
        when(areaConhecimentoRepository.findById(1L)).thenReturn(Optional.of(areaConhecimento));
        when(areaConhecimentoMapper.toResponseDTO(areaConhecimento)).thenReturn(responseDTO);

        // WHEN
        AreaConhecimentoResponseDTO resultado = areaConhecimentoService.buscarPorId(1L);

        // THEN
        assertEquals(responseDTO, resultado);

        verify(areaConhecimentoRepository).findById(1L);
        verify(areaConhecimentoMapper).toResponseDTO(areaConhecimento);
        verifyNoMoreInteractions(areaConhecimentoRepository, areaConhecimentoMapper);
    }

    @Test
    void deveLancarExcecaoQuandoAreaConhecimentoNaoForEncontradaPorId() {
        // GIVEN
        when(areaConhecimentoRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> areaConhecimentoService.buscarPorId(99L));

        assertThat(excecao.getMessage()).contains("99");

        verify(areaConhecimentoRepository).findById(99L);
        verifyNoInteractions(areaConhecimentoMapper);
        verifyNoMoreInteractions(areaConhecimentoRepository);
    }

    @Test
    void deveBuscarAreaConhecimentoPorNomeComSucesso() {
        // GIVEN
        when(areaConhecimentoRepository.findByNomeContainingIgnoreCase("Info")).thenReturn(List.of(areaConhecimento));
        when(areaConhecimentoMapper.toResponseDTO(areaConhecimento)).thenReturn(responseDTO);

        // WHEN
        List<AreaConhecimentoResponseDTO> resultado = areaConhecimentoService.buscarPorNome("Info");

        // THEN
        assertThat(resultado).hasSize(1);
        assertThat(resultado).containsExactly(responseDTO);

        verify(areaConhecimentoRepository).findByNomeContainingIgnoreCase("Info");
        verify(areaConhecimentoMapper).toResponseDTO(areaConhecimento);
        verifyNoMoreInteractions(areaConhecimentoRepository, areaConhecimentoMapper);
    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumaAreaForEncontradaPorNome() {
        // GIVEN
        when(areaConhecimentoRepository.findByNomeContainingIgnoreCase("Inexistente")).thenReturn(List.of());

        // WHEN
        List<AreaConhecimentoResponseDTO> resultado = areaConhecimentoService.buscarPorNome("Inexistente");

        // THEN
        assertThat(resultado).isEmpty();

        verify(areaConhecimentoRepository).findByNomeContainingIgnoreCase("Inexistente");
        verifyNoInteractions(areaConhecimentoMapper);
        verifyNoMoreInteractions(areaConhecimentoRepository);
    }

    @Test
    void deveListarTodasAsAreasDeConhecimento() {
        // GIVEN
        AreaConhecimento area2 = mock(AreaConhecimento.class);
        AreaConhecimentoResponseDTO responseDTO2 = new AreaConhecimentoResponseDTO(
                2L, "Saúde", "Cursos da área da saúde",
                LocalDateTime.now(), LocalDateTime.now());

        when(areaConhecimentoRepository.findAllByOrderByNomeAsc()).thenReturn(List.of(areaConhecimento, area2));
        when(areaConhecimentoMapper.toResponseDTO(areaConhecimento)).thenReturn(responseDTO);
        when(areaConhecimentoMapper.toResponseDTO(area2)).thenReturn(responseDTO2);

        // WHEN
        List<AreaConhecimentoResponseDTO> resultado = areaConhecimentoService.listar();

        // THEN
        assertThat(resultado).hasSize(2);
        assertThat(resultado).containsExactly(responseDTO, responseDTO2);

        verify(areaConhecimentoRepository).findAllByOrderByNomeAsc();
        verify(areaConhecimentoMapper).toResponseDTO(areaConhecimento);
        verify(areaConhecimentoMapper).toResponseDTO(area2);
        verifyNoMoreInteractions(areaConhecimentoRepository, areaConhecimentoMapper);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverAreasDeConhecimento() {
        // GIVEN
        when(areaConhecimentoRepository.findAllByOrderByNomeAsc()).thenReturn(List.of());

        // WHEN
        List<AreaConhecimentoResponseDTO> resultado = areaConhecimentoService.listar();

        // THEN
        assertThat(resultado).isEmpty();

        verify(areaConhecimentoRepository).findAllByOrderByNomeAsc();
        verifyNoInteractions(areaConhecimentoMapper);
        verifyNoMoreInteractions(areaConhecimentoRepository);
    }

    @Test
    void deveAtualizarAreaConhecimentoComSucesso() {
        // GIVEN
        when(areaConhecimentoRepository.findById(1L)).thenReturn(Optional.of(areaConhecimento));
        when(areaConhecimentoRepository.existsByNomeAndIdNot(requestDTO.nome(), 1L)).thenReturn(false);
        doNothing().when(areaConhecimentoMapper).updateEntityFromDTO(requestDTO, areaConhecimento);
        when(areaConhecimentoRepository.saveAndFlush(areaConhecimento)).thenReturn(areaConhecimento);
        when(areaConhecimentoMapper.toResponseDTO(areaConhecimento)).thenReturn(responseDTO);

        // WHEN
        AreaConhecimentoResponseDTO resultado = areaConhecimentoService.atualizar(1L, requestDTO);

        // THEN
        assertEquals(responseDTO, resultado);

        verify(areaConhecimentoRepository).findById(1L);
        verify(areaConhecimentoRepository).existsByNomeAndIdNot(requestDTO.nome(), 1L);
        verify(areaConhecimentoMapper).updateEntityFromDTO(requestDTO, areaConhecimento);
        verify(areaConhecimentoRepository).saveAndFlush(areaConhecimento);
        verify(areaConhecimentoMapper).toResponseDTO(areaConhecimento);
        verifyNoMoreInteractions(areaConhecimentoRepository, areaConhecimentoMapper);
    }

    @Test
    void deveLancarExcecaoAoAtualizarQuandoAreaConhecimentoNaoExistir() {
        // GIVEN
        when(areaConhecimentoRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> areaConhecimentoService.atualizar(99L, requestDTO));

        assertThat(excecao.getMessage()).contains("99");

        verify(areaConhecimentoRepository).findById(99L);
        verify(areaConhecimentoRepository, never()).saveAndFlush(any());
        verifyNoInteractions(areaConhecimentoMapper);
        verifyNoMoreInteractions(areaConhecimentoRepository);
    }

    @Test
    void deveLancarConflitoAoAtualizarQuandoNomeJaEstiverEmUso() {
        // GIVEN
        when(areaConhecimentoRepository.findById(1L)).thenReturn(Optional.of(areaConhecimento));
        when(areaConhecimentoRepository.existsByNomeAndIdNot(requestDTO.nome(), 1L)).thenReturn(true);

        // WHEN + THEN
        ConflictException excecao = assertThrows(ConflictException.class,
                () -> areaConhecimentoService.atualizar(1L, requestDTO));

        assertThat(excecao.getMessage()).contains(requestDTO.nome());

        verify(areaConhecimentoRepository).findById(1L);
        verify(areaConhecimentoRepository).existsByNomeAndIdNot(requestDTO.nome(), 1L);
        verify(areaConhecimentoRepository, never()).saveAndFlush(any());
        verifyNoInteractions(areaConhecimentoMapper);
        verifyNoMoreInteractions(areaConhecimentoRepository);
    }

    @Test
    void deveDeletarAreaConhecimentoComSucesso() {
        // GIVEN
        when(areaConhecimentoRepository.findById(1L)).thenReturn(Optional.of(areaConhecimento));
        doNothing().when(areaConhecimentoRepository).delete(areaConhecimento);

        // WHEN
        areaConhecimentoService.deletar(1L);

        // THEN
        verify(areaConhecimentoRepository).findById(1L);
        verify(areaConhecimentoRepository).delete(areaConhecimento);
        verifyNoMoreInteractions(areaConhecimentoRepository);
    }

    @Test
    void deveLancarExcecaoAoDeletarQuandoAreaConhecimentoNaoExistir() {
        // GIVEN
        when(areaConhecimentoRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN
        ResourceNotFoundException excecao = assertThrows(ResourceNotFoundException.class,
                () -> areaConhecimentoService.deletar(99L));

        assertThat(excecao.getMessage()).contains("99");

        verify(areaConhecimentoRepository).findById(99L);
        verify(areaConhecimentoRepository, never()).delete(any());
        verifyNoMoreInteractions(areaConhecimentoRepository);
    }
}
