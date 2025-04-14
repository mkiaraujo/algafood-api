package com.algaworks.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
@Schema(name = "UsuarioModel")
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Maricio")
    private String nome;

    @Schema(example = "mauricio@gmail.com")
    private String email;
}
