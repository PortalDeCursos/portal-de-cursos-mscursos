package com.projetointegrador.cursos.business.dto.response;

import com.projetointegrador.cursos.infrastructure.enums.SemestreEnum;

import java.time.LocalDateTime;

public record DisciplinaResponseDTO(

        Long id,
        Long cursoId,
        String nome,
        String codigo,
        Integer cargaHoraria,
        SemestreEnum semestre,
        String descricao,
        Boolean obrigatoria,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
