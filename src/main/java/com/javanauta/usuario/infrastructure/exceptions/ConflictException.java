package com.javanauta.usuario.infrastructure.exceptions;

public class ConflictException extends RuntimeException{

    public ConflictException(String mensagem){
        super(mensagem);
    }

    public ConflictException(String mensaqem, Throwable throwable){
        super(mensaqem);
    }
}
