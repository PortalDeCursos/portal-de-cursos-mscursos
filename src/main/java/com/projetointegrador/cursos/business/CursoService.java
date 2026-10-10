package com.projetointegrador.cursos.business;

import com.projetointegrador.cursos.business.dto.request.CursoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CursoResponseDTO;
import com.projetointegrador.cursos.business.mapper.CursoMapper;
import com.projetointegrador.cursos.infrastructure.entity.Curso;
import com.projetointegrador.cursos.infrastructure.enums.GrauEnum;
import com.projetointegrador.cursos.infrastructure.enums.ModalidadeEnum;
import com.projetointegrador.cursos.infrastructure.enums.TurnoEnum;
import com.projetointegrador.cursos.infrastructure.exception.ConflictException;
import com.projetointegrador.cursos.infrastructure.exception.ResourceNotFoundException;
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
            throw new ConflictException("Este nome já está em uso por outro curso: " + nome);
        }
    }

    private void verificarAcronimo(String acronimo) {

        if (cursoRepository.existsByAcronimoIgnoreCase(acronimo)) {
            throw new ConflictException("Este acrônimo já está em uso por outro curso: " + acronimo);
        }
    }

    @Transactional(readOnly = true)
    public CursoResponseDTO buscarPorId(Long id) {

        return cursoMapper.toResponseDTO(buscarEntidadePorId(id));
    }

    @Transactional(readOnly = true)
    public Page<CursoResponseDTO> listar(Pageable pageable) {

        return cursoRepository.findAll(pageable).map(cursoMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<CursoResponseDTO> buscar(GrauEnum grau, TurnoEnum turno,
                                         ModalidadeEnum modalidade, Pageable pageable) {

        Page<Curso> cursos;

        if (grau != null) {
            cursos = cursoRepository.findByGrau(grau, pageable);
        } else if (turno != null) {
            cursos = cursoRepository.findByTurno(turno, pageable);
        } else if (modalidade != null) {
            cursos = cursoRepository.findByModalidade(modalidade, pageable);
        } else {
            cursos = cursoRepository.findAll(pageable);
        }

        return cursos.map(cursoMapper::toResponseDTO);
    }

    private Curso buscarEntidadePorId(Long id) {

        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com esse id: " + id));
    }

    private void verificarNomeParaAtualizar(String nome, Long id) {

        if (cursoRepository.existsByNomeIgnoreCaseAndIdNot(nome, id)) {
            throw new ConflictException("Este nome já está em uso por outro curso: " + nome);
        }
    }

    private void verificarAcronimoParaAtualizar(String acronimo, Long id) {

        if (cursoRepository.existsByAcronimoIgnoreCaseAndIdNot(acronimo, id)) {
            throw new ConflictException("Este acrônimo já está em uso por outro curso: " + acronimo);
        }
    }

    @Transactional
    public CursoResponseDTO atualizar(Long id, CursoRequestDTO dto) {

        Curso entity = buscarEntidadePorId(id);

        verificarNomeParaAtualizar(dto.nome(), id);
        verificarAcronimoParaAtualizar(dto.acronimo(), id);

        cursoMapper.updateEntityFromDTO(dto, entity);

        return cursoMapper.toResponseDTO(cursoRepository.saveAndFlush(entity));
    }

    @Transactional
    public void deletar(Long id) {

        Curso entity = buscarEntidadePorId(id);

        cursoRepository.delete(entity);
    }
}
