package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.CampusRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CampusResponseDTO;
import com.projetointegrador.cursos.business.mapper.CampusMapper;
import com.projetointegrador.cursos.infrastructure.entity.Campus;
import com.projetointegrador.cursos.infrastructure.exception.ConflictException;
import com.projetointegrador.cursos.infrastructure.exception.ResourceNotFoundException;
import com.projetointegrador.cursos.infrastructure.repository.CampusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository campusRepository;
    private final CampusMapper campusMapper;

    @Transactional
    public CampusResponseDTO cadastrar(CampusRequestDTO dto) {

        verificarNome(dto.nome());
        verificarAcronimo(dto.acronimo());

        Campus campus = campusMapper.toEntity(dto);

        return campusMapper.toResponseDTO(campusRepository.save(campus));
    }

    private void verificarNome(String nome) {

        if (campusRepository.existsByNome(nome)) {
            throw new ConflictException("Já existe um Campus com esse nome: " + nome);
        }
    }

    private void verificarAcronimo(String acronimo) {

        if (campusRepository.existsByAcronimo(acronimo)) {
            throw new ConflictException("Já existe um Campus com esse acrônimo: " + acronimo);
        }
    }

    @Transactional(readOnly = true)
    public List<CampusResponseDTO> buscarPorNome(String nome) {

        return campusRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(campusMapper::toResponseDTO)
                .toList();
    }


    @Transactional(readOnly = true)
    public CampusResponseDTO buscarPorId(Long id) {

        Campus campus = buscarEntidadePorId(id);

        return campusMapper.toResponseDTO(campus);
    }

    private Campus buscarEntidadePorId(Long id) {

        return campusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campus não encontrado com id informado: " + id));
    }
}
