package com.projetointegrador.cursos.business.mapper;

import com.projetointegrador.cursos.business.dto.request.AreaConhecimentoRequestDTO;
import com.projetointegrador.cursos.business.dto.response.AreaConhecimentoResponseDTO;
import com.projetointegrador.cursos.infrastructure.entity.AreaConhecimento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaConhecimentoMapper {

    AreaConhecimento toEntity(AreaConhecimentoRequestDTO dto);

    AreaConhecimentoResponseDTO toResponseDTO(AreaConhecimento entity);
}
