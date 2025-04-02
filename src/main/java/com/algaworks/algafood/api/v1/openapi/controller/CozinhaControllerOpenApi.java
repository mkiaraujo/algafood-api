package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.core.springdoc.PageableParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
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
    @Operation(summary = "Lista as cozinhas com paginação", description = "Lista todas as cozinhas cadastradas com paginação",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(ref = "CozinhaModel")))
            })
    PagedModel<CozinhaModel> listar(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Busca uma cozinha por ID",
            description = "Busca uma cozinha passando um ID válido", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID da cozinha inválido",
                            content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                            content = @Content(schema = @Schema(ref = "Problema")))
    })
    CozinhaModel buscar(@Parameter(description = "ID de uma cozinha", example = "1") Long cozinhaId);

    @Operation(summary = "Cadastra uma cozinha",
            description = "Cadastro de uma cozinha necessita de um nome válido", responses = {
            @ApiResponse(responseCode = "201", description = "Cozinha cadastrada")
    })
    CozinhaModel adicionar(
            @RequestBody(description = "Representação de uma nova cozinha", required = true) CozinhaInput cozinhaInput);

    @Operation(summary = "Atualiza uma cozinha por ID",
            description = "Atualiza uma cozinha por um ID e um novo nome válido", responses = {
            @ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada" )
    })
    CozinhaModel atualizar(
            @Parameter(description = "Informe um ID", example = "1", required = true) Long cozinhaId,
            @RequestBody(description = "Representação de uma cozinha com novos dados") CozinhaInput cozinhaInput);

    @Operation(summary = "Remove uma cozinha pelo ID",
            description = "Remove uma cozinha passando um ID válido", responses = {
            @ApiResponse(responseCode = "204", description = "Cozinha excluída"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                            content = @Content(schema = @Schema(ref = "Problema")))
    })
    ResponseEntity<Void> remover(@Parameter(description = "Informe um ID", example = "1", required = true) Long cozinhaId);

}