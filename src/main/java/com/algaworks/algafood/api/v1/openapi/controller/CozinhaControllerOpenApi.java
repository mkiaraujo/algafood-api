package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.core.springdoc.PageableParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @PageableParameter
    @Operation(summary = "Lista as cozinhas", description = "Lista todas as cozinhas cadastradas",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(ref = "CozinhaModel")))
            })
    PagedModel<CozinhaModel> listar(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Busca uma cozinha por ID", description = "Busca uma cozinha passando um ID válido")
    CozinhaModel buscar(@Parameter(description = "ID de uma cozinha", example = "1") Long cozinhaId);

    @Operation(summary = "Cadastra uma cozinha", description = "Cadastro de uma cozinha necessita de um nome válido")
    CozinhaModel adicionar(
            @RequestBody(description = "Representação de uma nova cozinha", required = true) CozinhaInput cozinhaInput);

    @Operation(summary = "Atualiza uma cozinha", description = "Atualiza uma cozinha por um ID e um novo nome válido")
    CozinhaModel atualizar(
            @Parameter(description = "Informe um ID", example = "1", required = true) Long cozinhaId,
            @RequestBody(description = "Representação de uma cozinha com dados para atualizar") CozinhaInput cozinhaInput);

    @Operation(summary = "Remove uma cozinha pelo ID", description = "Remove uma cozinha passando um ID válido")
    ResponseEntity<Void> remover(@Parameter(description = "Informe um ID", example = "1") Long cozinhaId);

}