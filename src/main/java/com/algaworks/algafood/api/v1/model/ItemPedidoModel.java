package com.algaworks.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;


@Getter
@Setter
@Schema(name = "ItemPedidoModel")
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {

    @Schema(example = "1")
    private Long produtoId;

    @Schema(example = "Bife Ancho")
    private String produtoNome;

    @Schema(example = "2")
    private Integer quantidade;

    @Schema(example = "79,00")
    private BigDecimal precoUnitario;

    @Schema(example = "158,00")
    private BigDecimal precoTotal;

    @Schema(example = "Menos picante, por favor")
    private String observacao;

}
