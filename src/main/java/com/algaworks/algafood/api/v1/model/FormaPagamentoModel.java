package com.algaworks.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
@Schema(name = "FormaPagamentoModel")
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {

    @NotNull
    @Schema(example = "1")
    private Long id;

    @NotBlank
    @Schema(example = "Cartão de crédito")
    private String descricao;
}
