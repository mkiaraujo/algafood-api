package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaModel extends RepresentationModel<CozinhaModel> {

//    @JsonView(RestauranteView.Resumo.class)
    private Long id;

//    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
