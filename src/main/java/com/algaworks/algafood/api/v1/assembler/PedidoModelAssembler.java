package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Algalinks algalinks;

    public PedidoModelAssembler() {
        super(PedidoController.class , PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        var pedidoModel = createModelWithId(pedido.getCodigo() , pedido);
        modelMapper.map(pedido , pedidoModel);

        pedidoModel.add(algalinks.linkToPedidos("pedidos"));

        if (pedido.podeSerConfirmado()) {
            pedidoModel.add(algalinks.linkToConfirmacaoPedido(pedido.getCodigo() , "confirmar"));
        }

        if (pedido.podeSerCancelado()) {
            pedidoModel.add(algalinks.linkToCancelamentoPedido(pedido.getCodigo() , "cancelar"));
        }

        if (pedido.podeSerEntregue()) {
            pedidoModel.add(algalinks.linkToEntregaPedido(pedido.getCodigo() , "entregar"));
        }

        pedidoModel.getRestaurante().add(algalinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoModel.getCliente().add(algalinks.linkToUsuario(pedido.getCliente().getId()));

        pedidoModel.getFormaPagamento().add(algalinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        pedidoModel
                .getEnderecoEntrega()
                .getCidade()
                .add(algalinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

        pedidoModel.getItens().forEach(item -> {
            item.add(algalinks.linkToProduto(
                    pedidoModel.getRestaurante().getId() , item.getProdutoId() , "produtos"));
        });

        return pedidoModel;
    }
}
