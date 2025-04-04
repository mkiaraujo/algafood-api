package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

    @Operation(summary = "Lista as formas de pagamento",
            description = "Lista todas as formas de pagamento", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(ref = "FormaPagamentoModel")))
    })
    ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);

    @Operation(summary = "Busca uma forma de pagamento por ID",
            description = "Busca uma forma de pagamento passando um ID válido", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID da forma de pagamento inválido",
                content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
                content = @Content(schema = @Schema(ref = "Problema")))
    })
    ResponseEntity<FormaPagamentoModel> buscar(
            @Parameter(description = "Informe um ID válido", example = "1") Long formaPagamentoId,
            ServletWebRequest request);

    @Operation(summary = "Cadastra uma forma de pagamento",
            description = "Cadastra uma forma de pagamento informando uma representação válida", responses = {
            @ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada")
    })
    FormaPagamentoModel adicionar(
            @RequestBody(description = "Representação de uma forma de pagamento") FormaPagamentoInput formaPagamentoInput);

    @Operation(summary = "Atualiza uma forma de pagamento",
            description = "Atualiza uma forma de pagamento informando um ID e uma nova descrição válidos", responses = {
            @ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada"),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada")
    })
    FormaPagamentoModel atualizar(
            @Parameter(description = "Informe um ID", example = "1", required = true) Long formaPagamentoId,
            @Parameter(description = "Representação de uma forma de pagamento com novos dados")
            FormaPagamentoInput formaPagamentoInput);

    @Operation(summary = "Remove uma forma de pagamento",
            description = "Remove uma forma de pagamento por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Forma de pagamento excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
                    content = @Content(schema = @Schema(ref = "Problema")))
    })
    ResponseEntity<Void> remover(@Parameter(description = "Informe um ID", example = "1",
            required = true) Long formaPagamentoId);

}