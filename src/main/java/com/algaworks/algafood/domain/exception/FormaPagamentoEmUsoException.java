package com.algaworks.algafood.domain.exception;

public class FormaPagamentoEmUsoException extends EntidadeEmUsoException {

    public FormaPagamentoEmUsoException(String message) {
        super(message);
    }

    public FormaPagamentoEmUsoException(Long formaPagamentoId) {
        this(String.format("Forma de pagamento de código %d não pode ser removida, pois está em uso", formaPagamentoId));
    }
}
