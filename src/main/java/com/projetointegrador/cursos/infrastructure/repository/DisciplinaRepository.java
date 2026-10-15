package com.projetointegrador.cursos.infrastructure.repository;

import com.projetointegrador.cursos.infrastructure.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    boolean existsByCodigoIgnoreCase(String codigo);
}
