package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.CursoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CursoResponseDTO;
import com.projetointegrador.cursos.business.mapper.CursoMapper;
import com.projetointegrador.cursos.infrastructure.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    @Transactional
    public CursoResponseDTO cadastrar(CursoRequestDTO dto) {

        return cursoMapper.toResponseDTO(cursoRepository.save(cursoMapper.toEntity(dto)));
    }
}
