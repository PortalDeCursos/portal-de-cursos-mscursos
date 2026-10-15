package com.projetointegrador.cursos.business.dto.request;

import com.projetointegrador.cursos.infrastructure.enums.SemestreEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DisciplinaRequestDTO(

        @NotNull(message = "Id do curso é obrigatório")
        Long cursoId,

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "Código é obrigatório")
        @Size(max = 20, message = "Código deve ter no máximo 20 caracteres")
        String codigo,

        @NotNull(message = "Carga horária é obrigatória")
        @Positive(message = "Carga horária deve ser maior que zero")
        Integer cargaHoraria,

        @NotNull(message = "Semestre é obrigatório")
        SemestreEnum semestre,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
        String descricao,

        @NotNull(message = "Informe se a disciplina é obrigatória")
        Boolean obrigatoria
) {
}
