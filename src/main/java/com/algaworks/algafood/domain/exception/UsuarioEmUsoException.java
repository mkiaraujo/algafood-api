package com.algaworks.algafood.domain.exception;


public class UsuarioEmUsoException extends EntidadeEmUsoException{

    public UsuarioEmUsoException(String message) {
        super(message);
    }

    public UsuarioEmUsoException(Long usuarioId) {
        this(String.format("Usuário de código %d não pode ser removida, pois está em uso", usuarioId));
    }
}
