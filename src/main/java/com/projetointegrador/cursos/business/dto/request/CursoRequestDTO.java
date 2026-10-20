package com.projetointegrador.cursos.business.dto.request;

import com.projetointegrador.cursos.infrastructure.enums.GrauEnum;
import com.projetointegrador.cursos.infrastructure.enums.ModalidadeEnum;
import com.projetointegrador.cursos.infrastructure.enums.TurnoEnum;
import jakarta.validation.constraints.*;

public record CursoRequestDTO (

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "Acrônimo é obrigatório")
        @Size(max = 10, message = "Acrônimo deve ter no máximo 10 caracteres")
        String acronimo,

        @NotNull(message = "Grau é obrigatório")
        GrauEnum grau,

        @NotBlank(message = "Duração é obrigatória")
        @Size(max = 50, message = "Duração deve ter no máximo 50 caracteres")
        String duracao,

        @NotNull(message = "Turno é obrigatório")
        TurnoEnum turno,

        @NotNull(message = "Modalidade é obrigatória")
        ModalidadeEnum modalidade,

        @NotNull(message = "Carga horária é obrigatória")
        @Positive(message = "Carga horária deve ser maior que zero")
        Integer cargaHoraria,

        @NotNull(message = "Quantidade de disciplinas obrigatórias é obrigatória")
        @PositiveOrZero(message = "Quantidade de disciplinas obrigatórias não pode ser negativa")
        Integer qtdDisciplinasObrigatorias,

        @NotNull(message = "Quantidade de disciplinas optativas é obrigatória")
        @PositiveOrZero(message = "Quantidade de disciplinas optativas não pode ser negativa")
        Integer qtdDisciplinasOptativas,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
        String descricao,

        @NotNull(message = "Id do Campus é obrigatório")
        Long campusId
) {

}
