package com.algaworks.algafood.api.v1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {

    @NotNull
    private Long id;

    @NotBlank
    private String descricao;
}
