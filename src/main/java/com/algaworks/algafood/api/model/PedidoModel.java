package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoModel {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private String status;
    private FormaPagamentoModel formaPagamento;
    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;
    private EnderecoModel enderecoEntrega;
    private List<ItemPedidoModel> itens;


}
