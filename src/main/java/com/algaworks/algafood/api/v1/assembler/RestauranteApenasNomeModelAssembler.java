package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class RestauranteApenasNomeModelAssembler
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Algalinks algalinks;

    public RestauranteApenasNomeModelAssembler() {
        super(RestauranteController.class , RestauranteApenasNomeModel.class);
    }


    @Override
    public RestauranteApenasNomeModel toModel(Restaurante restaurante) {
        var restauranteApenasNomeModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteApenasNomeModel);

        restauranteApenasNomeModel.add(algalinks.linkToRestaurantes("restaurantes"));

        return restauranteApenasNomeModel;
    }

    @Override
    public CollectionModel<RestauranteApenasNomeModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(algalinks.linkToRestaurantes());
    }
}
