package com.projetointegrador.cursos.business.dto.response;

import com.projetointegrador.cursos.infrastructure.enums.GrauEnum;
import com.projetointegrador.cursos.infrastructure.enums.ModalidadeEnum;
import com.projetointegrador.cursos.infrastructure.enums.TurnoEnum;

import java.time.LocalDateTime;

public record CursoResponseDTO(

        Long id,
        String nome,
        String acronimo,
        GrauEnum grau,
        String duracao,
        TurnoEnum turno,
        ModalidadeEnum modalidade,
        Integer cargaHoraria,
        Integer qtdDisciplinasObrigatorias,
        Integer qtdDisciplinasOptativas,
        String descricao,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
