package com.projetointegrador.cursos.infrastructure.repository;

import com.projetointegrador.cursos.infrastructure.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Long> {

    boolean existsByNome(String nome);
    boolean existsByAcronimo(String acronimo);
}
