package com.projetointegrador.cursos.business.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CampusRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "Acrônimo é obrigatório")
        @Size(max = 10, message = "Acrônimo deve ter no máximo 10 caracteres")
        String acronimo,

        @NotBlank(message = "Cidade é obrigatório")
        @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
        String cidade,

        @NotBlank(message = "Endereço é obrigatório")
        @Size(max = 200, message = "Endereço deve ter no máximo 200 caracteres")
        String endereco
) {
}
