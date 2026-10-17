package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.AreaConhecimentoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.AreaConhecimentoResponseDTO;
import com.projetointegrador.cursos.business.mapper.AreaConhecimentoMapper;
import com.projetointegrador.cursos.infrastructure.entity.AreaConhecimento;
import com.projetointegrador.cursos.infrastructure.exception.ConflictException;
import com.projetointegrador.cursos.infrastructure.repository.AreaConhecimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaConhecimentoService {

    private final AreaConhecimentoRepository areaConhecimentoRepository;
    private final AreaConhecimentoMapper areaConhecimentoMapper;

    @Transactional
    public AreaConhecimentoResponseDTO cadastrar(AreaConhecimentoRequestDTO dto) {

        verificarNome(dto.nome());

        AreaConhecimento areaConhecimento = areaConhecimentoMapper.toEntity(dto);

        return areaConhecimentoMapper.toResponseDTO(areaConhecimentoRepository.save(areaConhecimento));
    }

    private void verificarNome(String nome) {

        if (areaConhecimentoRepository.existsByNome(nome)) {
            throw new ConflictException("Nome informado não disponível: " + nome);
        }
    }

    @Transactional(readOnly = true)
    public List<AreaConhecimentoResponseDTO> buscarPorNome(String nome) {

        return areaConhecimentoRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(areaConhecimentoMapper::toResponseDTO)
                .toList();
    }
}
