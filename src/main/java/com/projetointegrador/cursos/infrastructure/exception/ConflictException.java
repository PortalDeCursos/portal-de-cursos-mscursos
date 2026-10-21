package com.projetointegrador.cursos.infrastructure.exception;

public class ConflictException extends RuntimeException {

    public ConflictException(String mensagem) {
        super(mensagem);
    }
}
