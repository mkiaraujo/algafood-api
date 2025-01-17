package com.algaworks.algafood.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel {

    @NotNull
    private Long id;

    @NotBlank
    private String descricao;
}
