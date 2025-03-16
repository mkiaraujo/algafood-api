package com.algaworks.algafood.api.v1.model.input;

import com.algaworks.algafood.core.validation.ValorZeroIncluiDescricao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome",
        descricaoObrigatoria = "Frete Grátis")
public class RestauranteInput {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdInput cozinha;

    @Valid
    @NotNull
    private EnderecoInput endereco;
}
