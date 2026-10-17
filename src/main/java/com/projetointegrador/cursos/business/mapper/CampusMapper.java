package com.projetointegrador.cursos.business.mapper;

import com.projetointegrador.cursos.business.dto.request.CampusRequestDTO;
import com.projetointegrador.cursos.business.dto.response.CampusResponseDTO;
import com.projetointegrador.cursos.infrastructure.entity.Campus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CampusMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    Campus toEntity(CampusRequestDTO dto);

    CampusResponseDTO toResponseDTO(Campus entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    void updateEntityFromDTO(CampusRequestDTO dto, @MappingTarget Campus entiyt);
}
