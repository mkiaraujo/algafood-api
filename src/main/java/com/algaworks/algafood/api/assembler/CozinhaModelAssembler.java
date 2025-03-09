package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.Algalinks;
import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Algalinks algalinks;

    public CozinhaModelAssembler() {
        super(CozinhaController.class , CozinhaModel.class);
    }

    @Override
    public CozinhaModel toModel(Cozinha cozinha){
        var cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModel);

        cozinhaModel.add(algalinks.linkToCozinhas("cozinhas"));

        return cozinhaModel;
    }

}
