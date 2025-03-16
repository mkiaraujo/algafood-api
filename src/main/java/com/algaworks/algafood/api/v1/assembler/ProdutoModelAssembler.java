package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v1.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Algalinks algalinks;

    public ProdutoModelAssembler() {
        super(RestauranteProdutoController.class , ProdutoModel.class);
    }

    @Override
    public ProdutoModel toModel(Produto produto){
        var produtoModel = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());
        modelMapper.map(produto, produtoModel);

        produtoModel.add(algalinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

        produtoModel.add(algalinks.linkToFotoProduto(produto.getRestaurante().getId(),produto.getId(), "foto"));

        return produtoModel;
    }
}
