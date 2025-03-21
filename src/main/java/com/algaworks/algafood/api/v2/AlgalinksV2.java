package com.algaworks.algafood.api.v2;

import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v2.controller.CidadeControllerV2;
import com.algaworks.algafood.api.v2.controller.CozinhaControllerV2;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlgalinksV2 {

    public Link linkToCidades(String rel){
        return linkTo(CidadeControllerV2.class).withRel(rel);
    }
    public Link linkToCidades(){
        return linkToCidades(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCozinhas(String rel){
        return linkTo(CozinhaControllerV2.class).withRel(rel);
    }

    public Link linkToCozinhas() {
        return linkToCozinhas(IanaLinkRelations.SELF_VALUE);
    }


}
