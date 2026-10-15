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

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaMapper disciplinaMapper;
    private final CursoRepository cursoRepository;

    @Transactional
    public DisciplinaResponseDTO cadastrar(DisciplinaRequestDTO dto) {

        Curso curso = buscarCurso(dto.cursoId());

        verificarCodigo(dto.codigo());

        Disciplina entity = disciplinaMapper.toEntity(dto);

        entity.setCurso(curso);

        return disciplinaMapper.toResponseDTO(disciplinaRepository.save(entity));
    }

    private Curso buscarCurso(Long cursoId) {

        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + cursoId));
    }

    private void verificarCodigo(String codigo) {

        if (disciplinaRepository.existsByCodigoIgnoreCase(codigo)) {
            throw new ConflictException("Código já em uso por outra disciplina: " + codigo);
        }
    }

    @Transactional(readOnly = true)
    public List<DisciplinaResponseDTO> listarPorCurso(Long cursoId) {

        buscarCurso(cursoId);

        return disciplinaRepository.findByCursoId(cursoId)
                .stream()
                .map(disciplinaMapper::toResponseDTO)
                .toList();
    }
}
