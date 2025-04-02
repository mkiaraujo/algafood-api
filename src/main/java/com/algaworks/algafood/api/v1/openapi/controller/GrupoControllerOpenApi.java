package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoControllerOpenApi {

    @Operation(summary = "Lista os grupos", description = "Lista todos os grupos cadastrados",
            responses = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(ref = "GrupoModel")))
    })
    CollectionModel<GrupoModel> listar();

    @Operation(summary = "Lista um grupo por ID", description = "Lista um grupo passando um ID válido")
    GrupoModel buscar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

    @Operation(summary = "Cadastra um novo grupo", description = "Cadastro de um grupo necessita de um nome válido")
    GrupoModel adicionar(@RequestBody(description = "Representação de um novo grupo", required = true) GrupoInput grupoInput);

    @Operation(summary = "Atualiza um grupo por ID", description = "Atualiza um grupo passando um ID " +
            "e um novo nome válido")
    GrupoModel atualizar(
            @Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId,
            @RequestBody(description = "Representação de um grupo com dados para atualizar", required = true) GrupoInput grupoInput);

    @Operation(summary = "Remove um grupo pelo ID", description = "Remove um grupo passando um ID válido")
    ResponseEntity<Void> remover(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

}