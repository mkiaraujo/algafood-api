package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

    @Operation(summary = "Lista as cidades", description = "Lista todas as cidades cadastradas")
    CollectionModel<CidadeModel> listar();

    @Operation(summary = "Busca uma cidade por id", description = "Busca uma cidade informando um id válido")
    CidadeModel buscar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);

    @Operation(summary = "Cadastra uma cidade", description = "Cadastro de uma cidade necessita de um estado " +
            "e um nome válido")
    CidadeModel adicionar(
            @RequestBody(description = "Representação de uma nova cidade", required = true) CidadeInput cidadeInput);

    @Operation(summary = "Atualiza uma cidade", description = "Atualiza uma cidade informando seu id " +
            " e um objeto contendo novo nome da cidade e um estdo com seu id válido")
    CidadeModel atualizar(
            @Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId,
            @RequestBody(description = "Representação de uma cidade com dados para atualizar", required = true)
            CidadeInput cidadeInput);

    @Operation(summary = "Remove uma cidade pelo id", description = "Remove uma cidade informando um id válido")
    ResponseEntity<Void> remover(
            @Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);

}