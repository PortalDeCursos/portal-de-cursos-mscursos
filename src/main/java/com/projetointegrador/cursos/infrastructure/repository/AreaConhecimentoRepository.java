package com.projetointegrador.cursos.infrastructure.repository;

import com.projetointegrador.cursos.infrastructure.entity.AreaConhecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaConhecimentoRepository extends JpaRepository<AreaConhecimento, Long> {

    boolean existsByNome(String nome);
    boolean existsByNomeAndIdNot(String nome, Long id);

    List<AreaConhecimento> findByNomeContainingIgnoreCase(String nome);
    List<AreaConhecimento> findAllByOrderByNomeAsc();
}
