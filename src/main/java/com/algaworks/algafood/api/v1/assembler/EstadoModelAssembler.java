package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v1.controller.EstadoController;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Algalinks algalinks;

    public EstadoModelAssembler() {
        super(EstadoController.class , EstadoModel.class);
    }

    @Override
    public EstadoModel toModel(Estado estado) {
        var estadoModel = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoModel);

        estadoModel.add(algalinks.linkToEstados("estados"));

        return estadoModel;
    }

    @Override
    public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
                .add(algalinks.linkToEstados());
    }
}
