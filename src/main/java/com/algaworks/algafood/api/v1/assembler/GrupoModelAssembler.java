package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v1.controller.GrupoController;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Algalinks algalinks;

    public GrupoModelAssembler() {
        super(GrupoController.class , GrupoModel.class);
    }

    @Override
    public GrupoModel toModel(Grupo grupo){
        var grupoModel = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoModel);

        grupoModel.add(algalinks.linkToGrupos("grupos"));

        grupoModel.add(algalinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));

        return  grupoModel;
    }

    @Override
    public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
        return super.toCollectionModel(entities).add(algalinks.linkToGrupos());
    }

}
