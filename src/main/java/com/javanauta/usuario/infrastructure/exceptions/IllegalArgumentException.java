package com.javanauta.usuario.infrastructure.exceptions;

public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String messagem) {
        super(messagem);
    }

    public IllegalArgumentException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }

}
