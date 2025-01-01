package com.algaworks.algafood.domain.exception;

public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem){
        super(mensagem);
    }

    public NegocioException(String message , Throwable cause) {
        super(message , cause);
    }
}
