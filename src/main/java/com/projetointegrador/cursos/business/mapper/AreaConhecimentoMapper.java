package com.projetointegrador.cursos.business.mapper;

import com.projetointegrador.cursos.business.dto.request.AreaConhecimentoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.AreaConhecimentoResponseDTO;
import com.projetointegrador.cursos.infrastructure.entity.AreaConhecimento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AreaConhecimentoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    AreaConhecimento toEntity(AreaConhecimentoRequestDTO dto);

    AreaConhecimentoResponseDTO toResponseDTO(AreaConhecimento entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    void updateEntityFromDTO(AreaConhecimentoRequestDTO dto, @MappingTarget AreaConhecimento entity);
}
