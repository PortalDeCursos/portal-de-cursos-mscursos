package com.projetointegrador.cursos.business.mapper;

import com.projetointegrador.cursos.business.dto.request.CursoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CursoResponseDTO;
import com.projetointegrador.cursos.infrastructure.entity.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    @Mapping(target = "disciplinas", ignore = true)
    @Mapping(target = "campus", ignore = true)
    Curso toEntity(CursoRequestDTO dto);

    @Mapping(target = "campusId", source = "campus.id")
    CursoResponseDTO toResponseDTO(Curso entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    @Mapping(target = "disciplinas", ignore = true)
    @Mapping(target = "campus", ignore = true)
    void updateEntityFromDTO(CursoRequestDTO dto, @MappingTarget Curso entity);
}
