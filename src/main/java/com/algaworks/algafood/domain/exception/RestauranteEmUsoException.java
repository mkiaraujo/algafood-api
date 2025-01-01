package com.algaworks.algafood.domain.exception;

public class RestauranteEmUsoException extends EntidadeEmUsoException {
    public RestauranteEmUsoException(String message){
        super(message);
    }

    public RestauranteEmUsoException(Long restauranteId) {
        this(String.format("Restaurante de código %d não pode ser removida, pois está em uso", restauranteId));
    }
}