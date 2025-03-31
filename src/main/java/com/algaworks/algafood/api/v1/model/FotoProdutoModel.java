package com.algaworks.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "fotos")
@Setter
@Getter
@Schema(name = "FotoProdutoModel")
public class FotoProdutoModel extends RepresentationModel<FotoProdutoModel> {

    @Schema(example = "foto")
    private String nomeArquivo;

    @Schema(example = "Arroz com pequi")
    private String descricao;

    @Schema(example = "json")
    private String contentType;

    @Schema(example = "430B")
    private Long tamanho;
}
