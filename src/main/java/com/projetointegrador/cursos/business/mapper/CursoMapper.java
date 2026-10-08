package com.projetointegrador.cursos.business.mapper;

import com.projetointegrador.cursos.business.dto.request.CursoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CursoResponseDTO;
import com.projetointegrador.cursos.infrastructure.entity.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    Curso toEntity(CursoRequestDTO dto);

    CursoResponseDTO toResponseDTO(Curso entity);
}
