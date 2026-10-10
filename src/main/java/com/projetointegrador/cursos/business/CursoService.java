package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.CursoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CursoResponseDTO;
import com.projetointegrador.cursos.business.mapper.CursoMapper;
import com.projetointegrador.cursos.infrastructure.exception.ConflictException;
import com.projetointegrador.cursos.infrastructure.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    @Transactional
    public CursoResponseDTO cadastrar(CursoRequestDTO dto) {

        verificarNome(dto.nome());
        verificarAcronimo(dto.acronimo());

        return cursoMapper.toResponseDTO(cursoRepository.save(cursoMapper.toEntity(dto)));
    }

    private void verificarNome(String nome) {

        if (cursoRepository.existsByNomeIgnoreCase(nome)) {
            throw new ConflictException("Este nome ja está em uso por outro curso: " + nome);
        }
    }

    private void verificarAcronimo(String acronimo) {

        if (cursoRepository.existsByAcronimoIgnoreCase(acronimo)) {
            throw new ConflictException("Este acrônimo ja está em uso por outro curso: " + acronimo);
        }
    }

    @Transactional(readOnly = true)
    public Page<CursoResponseDTO> listar(Pageable pageable) {

        return cursoRepository.findAll(pageable).map(cursoMapper::toResponseDTO);
    }
}
