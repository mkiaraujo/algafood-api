package com.algaworks.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

//@JsonFilter("pedidoFilter")
@Relation(collectionRelation = "pedidos")
@Getter
@Setter
@Schema(name = "PedidoResumoModel")
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {

    @Schema(example = "b5741512-8fbc-47fa-9ac1-b530354fc0ff")
    private String codigo;

    @Schema(example = "110.00")
    private BigDecimal subtotal;

    @Schema(example = "10.00")
    private BigDecimal taxaFrete;

    @Schema(example = "120.00")
    private BigDecimal valorTotal;

    @Schema(example = "ENTREGUE")
    private String status;

    @Schema(example = "2019-10-30T21:10:00Z")
    private OffsetDateTime dataCriacao;

    @Schema(example = "Restaurante onde foi feito o pedido")
    private RestauranteApenasNomeModel restaurante;

    @Schema(example = "Cliente que realizou o pedido")
    private UsuarioModel cliente;

}
