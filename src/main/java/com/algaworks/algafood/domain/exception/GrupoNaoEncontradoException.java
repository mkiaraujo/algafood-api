package com.algaworks.algafood.domain.exception;


public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public GrupoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long grupoId) {
        this(String.format("Não existe uma cadastro de grupo com código %d", grupoId));
    }
}
