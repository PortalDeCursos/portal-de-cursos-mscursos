package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.DisciplinaRequestDTO;
import com.projetointegrador.cursos.business.dto.response.DisciplinaResponseDTO;
import com.projetointegrador.cursos.business.mapper.DisciplinaMapper;
import com.projetointegrador.cursos.infrastructure.entity.Curso;
import com.projetointegrador.cursos.infrastructure.entity.Disciplina;
import com.projetointegrador.cursos.infrastructure.exception.ConflictException;
import com.projetointegrador.cursos.infrastructure.exception.ResourceNotFoundException;
import com.projetointegrador.cursos.infrastructure.repository.CursoRepository;
import com.projetointegrador.cursos.infrastructure.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaMapper disciplinaMapper;
    private final CursoRepository cursoRepository;

    @Transactional
    public DisciplinaResponseDTO cadastrar(DisciplinaRequestDTO dto) {

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + dto.cursoId()));

        verificarCodigo(dto.codigo());

        Disciplina entity = disciplinaMapper.toEntity(dto);

        entity.setCurso(curso);

        return disciplinaMapper.toResponseDTO(disciplinaRepository.save(entity));
    }

    private void verificarCodigo(String codigo) {

        if (disciplinaRepository.existsByCodigoIgnoreCase(codigo)) {
            throw new ConflictException("Código já em uso por outra disciplina: " + codigo);
        }
    }
}
