package com.algaworks.algafood.api.v2.model;

import com.algaworks.algafood.api.v1.model.EstadoModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {
    private Long idCidade;
    private String nomeCidade;
    private Long idEstado;
    private String nomeEstado;
}
