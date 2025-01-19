package com.algaworks.algafood.domain.exception;

public class GrupoEmUsoException extends EntidadeEmUsoException {
    public GrupoEmUsoException(String message){
        super(message);
    }

    public GrupoEmUsoException(Long grupoId) {
        this(String.format("Grupo de código %d não pode ser removida, pois está em uso", grupoId));
    }
}