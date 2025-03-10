package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public FormaPagamentoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoNaoEncontradaException(Long formaPagamentoId) {
        this(String.format("Não existe uma forma de pagamento com código %d", formaPagamentoId));
    }

    public FormaPagamentoNaoEncontradaException(Long restauranteId , Long formaPagamentoId) {
        this(String.format("Não existe uma forma de pagamento com código %d " +
                "para o restaurante com código %d", formaPagamentoId, restauranteId));
    }
}
