package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.DisciplinaRequestDTO;
import com.projetointegrador.cursos.business.dto.response.DisciplinaResponseDTO;
import com.projetointegrador.cursos.business.mapper.DisciplinaMapper;
import com.projetointegrador.cursos.infrastructure.entity.Curso;
import com.projetointegrador.cursos.infrastructure.entity.Disciplina;
import com.projetointegrador.cursos.infrastructure.enums.SemestreEnum;
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

        verificarNome(dto.nome());
        verificarCodigo(dto.codigo());

        Disciplina entity = disciplinaMapper.toEntity(dto);

        entity.setCurso(curso);

        return disciplinaMapper.toResponseDTO(disciplinaRepository.save(entity));
    }

    private Curso buscarCurso(Long cursoId) {

        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + cursoId));
    }

    private void verificarNome(String nome) {

        if (disciplinaRepository.existsByNomeIgnoreCase(nome)) {
            throw new ConflictException("Nome já em uso por outra disciplina: " + nome);
        }
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

    @Transactional(readOnly = true)
    public List<DisciplinaResponseDTO> filtrar(Long cursoId, SemestreEnum semestre, Boolean obrigatoria) {

        buscarCurso(cursoId);

        List<Disciplina> disciplinas;

        if (semestre != null && obrigatoria != null) {
            disciplinas = disciplinaRepository.findByCursoIdAndSemestreAndObrigatoria(cursoId, semestre, obrigatoria);

        } else if (semestre != null) {
            disciplinas = disciplinaRepository.findByCursoIdAndSemestre(cursoId, semestre);

        } else if (obrigatoria != null) {
            disciplinas = disciplinaRepository.findByCursoIdAndObrigatoria(cursoId, obrigatoria);

        } else {
            disciplinas = disciplinaRepository.findByCursoId(cursoId);
        }

        return disciplinas.stream().map(disciplinaMapper::toResponseDTO).toList();
    }

    @Transactional
    public DisciplinaResponseDTO atualizar(Long id, DisciplinaRequestDTO dto) {

        Disciplina entity = buscarEntidadePorId(id);

        verificarNomeParaAtualizar(dto.nome(), id);
        verificarCodigoParaAtualizar(dto.codigo(), id);

        disciplinaMapper.updateEntityFromDTO(dto, entity);

        return disciplinaMapper.toResponseDTO(disciplinaRepository.saveAndFlush(entity));
    }

    private Disciplina buscarEntidadePorId(Long id) {

        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com id: " + id));
    }

    private void verificarNomeParaAtualizar(String nome, Long id) {

        if (disciplinaRepository.existsByNomeIgnoreCaseAndIdNot(nome, id)) {
            throw new ConflictException("Este nome já está em uso por outra disciplina: " + nome);
        }
    }

    private void verificarCodigoParaAtualizar(String codigo, Long id) {

        if (disciplinaRepository.existsByCodigoIgnoreCaseAndIdNot(codigo, id)) {
            throw new ConflictException("Este código já está em uso por outra disciplina: " + codigo);
        }
    }

    @Transactional
    public void deletar(Long id) {

        Disciplina entity = buscarEntidadePorId(id);

        disciplinaRepository.delete(entity);
    }
}
