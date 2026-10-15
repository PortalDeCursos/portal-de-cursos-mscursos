package com.projetointegrador.cursos.infrastructure.repository;

import com.projetointegrador.cursos.infrastructure.entity.Disciplina;
import com.projetointegrador.cursos.infrastructure.enums.SemestreEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    boolean existsByCodigoIgnoreCase(String codigo);

    List<Disciplina> findByCursoId(Long cursoId);
    List<Disciplina> findByCursoIdAndSemestreAndObrigatoria(
            Long cursoId,
            SemestreEnum semestre,
            Boolean obrigatoria
    );
    List<Disciplina> findByCursoIdAndSemestre(
            Long cursoId,
            SemestreEnum semestre
    );
    List<Disciplina> findByCursoIdAndObrigatoria(
            Long cursoId,
            Boolean obrigatoria
    );
}
