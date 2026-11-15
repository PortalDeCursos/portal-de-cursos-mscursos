package com.projetointegrador.cursos.infrastructure.exception;

public class ServicoIndisponivelException extends RuntimeException {

    public ServicoIndisponivelException(String servico) {
        super("Serviço indisponível: " + servico);
    }
}
