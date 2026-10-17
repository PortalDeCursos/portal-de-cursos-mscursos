package com.projetointegrador.cursos.infrastructure.repository;

import com.projetointegrador.cursos.infrastructure.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Long> {

    boolean existsByNome(String nome);
    boolean existsByAcronimo(String acronimo);

    List<Campus> findByNomeContainingIgnoreCase(String nome);
    List<Campus> findAllByOrderByNomeAsc();
    List<Campus> findByCidadeIgnoreCase(String cidade);
}
