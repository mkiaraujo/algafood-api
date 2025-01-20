package com.algaworks.algafood.domain.exception;

public class UsuarioSenhaAtualNaoConfereException extends NegocioException {

    public UsuarioSenhaAtualNaoConfereException(String mensagem) {
        super(mensagem);
    }

    public UsuarioSenhaAtualNaoConfereException(Long usuarioId) {
        this(String.format("Senha atual informada do usuário %d não coincide", usuarioId));
    }
}
