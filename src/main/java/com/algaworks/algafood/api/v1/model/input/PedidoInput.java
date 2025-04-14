package com.algaworks.algafood.api.v1.model.input;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "PedidoInput")
public class PedidoInput {

    @Valid
    @NotNull
    @Schema(description = "restaurante")
    private RestauranteIdInput restaurante;

    @Valid
    @NotNull
    @Schema(description = "formaPagamento")
    private FormaPagamentoIdInput formaPagamento;

    @Valid
    @NotNull
    @Schema(description = "enderecoEntrega")
    private EnderecoInput enderecoEntrega;

    @Valid
    @Size(min = 1)
    @NotNull
    @Schema(description = "itens")
    private List<ItemPedidoInput> itens;
}
