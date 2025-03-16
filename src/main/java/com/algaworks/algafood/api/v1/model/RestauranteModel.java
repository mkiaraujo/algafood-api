package com.algaworks.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

//    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private Long id;

//    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private String nome;

//    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

//    @JsonView(RestauranteView.Resumo.class)
    private CozinhaModel cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoModel endereco;
}
