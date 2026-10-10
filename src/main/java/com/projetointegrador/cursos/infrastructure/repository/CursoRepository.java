package com.projetointegrador.cursos.infrastructure.repository;

import com.projetointegrador.cursos.infrastructure.entity.Curso;
import com.projetointegrador.cursos.infrastructure.enums.GrauEnum;
import com.projetointegrador.cursos.infrastructure.enums.ModalidadeEnum;
import com.projetointegrador.cursos.infrastructure.enums.TurnoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    boolean existsByNomeIgnoreCase(String nome);
    boolean existsByAcronimoIgnoreCase(String acronimo);

    Page<Curso> findByGrau(GrauEnum grau, Pageable pageable);
    Page<Curso> findByTurno(TurnoEnum turno, Pageable pageable);
    Page<Curso> findByModalidade(ModalidadeEnum modalidade, Pageable pageable);
}
