package com.algaworks.algafood.domain.exception;

public class CidadeEmUsoException extends EntidadeEmUsoException {
    public CidadeEmUsoException(String message){
        super(message);
    }

    public CidadeEmUsoException(Long cidadeId) {
        this(String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
    }
}