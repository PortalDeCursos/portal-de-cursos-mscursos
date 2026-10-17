package com.projetointegrador.cursos.business.dto.response;

import java.time.LocalDateTime;

public record CampusResponseDTO(

        Long id,
        String nome,
        String acronimo,
        String cidade,
        String endereco,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
