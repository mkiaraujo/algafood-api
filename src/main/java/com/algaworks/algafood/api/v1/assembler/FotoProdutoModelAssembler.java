package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Algalinks algalinks;

    public FotoProdutoModelAssembler() {
        super(RestauranteProdutoFotoController.class , FotoProdutoModel.class);
    }


    public FotoProdutoModel toModel(FotoProduto fotoProduto) {
        var fotoProdutoModel = modelMapper.map(fotoProduto, FotoProdutoModel.class);

        fotoProdutoModel.add(algalinks.linkToFotoProduto(
                fotoProduto.getRestauranteId(),
                fotoProduto.getProduto().getId()));

        fotoProdutoModel.add(algalinks.linkToProduto(
                fotoProduto.getRestauranteId(),
                fotoProduto.getProduto().getId(), "produto"));

        return fotoProdutoModel;
    }
}
