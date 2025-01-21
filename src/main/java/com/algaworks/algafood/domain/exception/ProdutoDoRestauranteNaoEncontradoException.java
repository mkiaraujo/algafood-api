package com.algaworks.algafood.domain.exception;

public class ProdutoDoRestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoDoRestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoDoRestauranteNaoEncontradoException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de produto de código %d no restaurante de código %d", produtoId, restauranteId));
    }
}
