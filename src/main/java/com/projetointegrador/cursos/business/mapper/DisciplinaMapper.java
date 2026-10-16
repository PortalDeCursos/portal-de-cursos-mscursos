package com.projetointegrador.cursos.business.mapper;

import com.projetointegrador.cursos.business.dto.request.DisciplinaRequestDTO;
import com.projetointegrador.cursos.business.dto.response.DisciplinaResponseDTO;
import com.projetointegrador.cursos.infrastructure.entity.Disciplina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    @Mapping(target = "curso", ignore = true)
    Disciplina toEntity(DisciplinaRequestDTO dto);

    @Mapping(target = "cursoId", source = "curso.id")
    DisciplinaResponseDTO toResponseDTO(Disciplina entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    @Mapping(target = "curso", ignore = true)
    void updateEntityFromDTO(DisciplinaRequestDTO dto, @MappingTarget Disciplina entity);
}
