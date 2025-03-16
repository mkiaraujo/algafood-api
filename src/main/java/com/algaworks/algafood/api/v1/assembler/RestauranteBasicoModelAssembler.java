package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class RestauranteBasicoModelAssembler
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Algalinks algalinks;

    public RestauranteBasicoModelAssembler() {
        super(RestauranteController.class , RestauranteBasicoModel.class);
    }


    @Override
    public RestauranteBasicoModel toModel(Restaurante restaurante) {
        var restauranteBasicoModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteBasicoModel);

        restauranteBasicoModel.add(algalinks.linkToRestaurantes("restaurantes"));

        restauranteBasicoModel
                .getCozinha().add(algalinks.linkToCozinha(restaurante.getCozinha().getId()));

        return restauranteBasicoModel;
    }

    @Override
    public CollectionModel<RestauranteBasicoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(algalinks.linkToRestaurantes());
    }
}
