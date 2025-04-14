package com.algaworks.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
@Schema(name = "PedidoModel")
public class PedidoModel extends RepresentationModel<PedidoModel> {

    @Schema(example = "b5741512-8fbc-47fa-9ac1-b530354fc0ff")
    private String codigo;

    @Schema(example = "110,00")
    private BigDecimal subtotal;

    @Schema(example = "10,00")
    private BigDecimal taxaFrete;

    @Schema(example = "120,00")
    private BigDecimal valorTotal;

    @Schema(example = "2019-10-30T21:10:00Z")
    private OffsetDateTime dataCriacao;

    @Schema(example = "2019-10-30T21:10:00Z")
    private OffsetDateTime dataConfirmacao;

    @Schema(example = "2019-10-30T21:10:00Z")
    private OffsetDateTime dataCancelamento;

    @Schema(example = "2019-10-30T21:10:00Z")
    private OffsetDateTime dataEntrega;

    @Schema(example = "ENTREGUE")
    private String status;

    @Schema(example = "Cartão de crédito")
    private FormaPagamentoModel formaPagamento;

    @Schema(description = "restaurante")
    private RestauranteApenasNomeModel restaurante;

    @Schema(description = "cliente")
    private UsuarioModel cliente;

    @Schema(description = "endereço de entrega")
    private EnderecoModel enderecoEntrega;

    @Schema(description = "itens")
    private List<ItemPedidoModel> itens;


}
