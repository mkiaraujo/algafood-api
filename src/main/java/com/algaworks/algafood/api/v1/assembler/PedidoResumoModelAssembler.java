package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Algalinks algalinks;

    public PedidoResumoModelAssembler() {
        super(PedidoController.class , PedidoResumoModel.class);
    }

    @Override
    public PedidoResumoModel toModel(Pedido pedido) {
        var pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoResumoModel);

        pedidoResumoModel.add(algalinks.linkToPedidos("pedidos"));

        pedidoResumoModel.getRestaurante().add(algalinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoResumoModel.getCliente().add(algalinks.linkToUsuario(pedido.getCliente().getId()));

        return pedidoResumoModel;
    }
}
