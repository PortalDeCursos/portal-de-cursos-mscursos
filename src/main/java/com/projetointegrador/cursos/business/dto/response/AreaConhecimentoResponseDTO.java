package com.projetointegrador.cursos.business.dto.response;

import java.time.LocalDateTime;

public record AreaConhecimentoResponseDTO(

        Long id,
        String nome,
        String descricao,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
