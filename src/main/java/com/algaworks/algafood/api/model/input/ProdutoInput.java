package com.algaworks.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;
}
