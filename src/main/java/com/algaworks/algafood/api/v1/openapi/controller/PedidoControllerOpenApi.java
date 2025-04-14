package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.core.springdoc.PageableParameter;
import com.algaworks.algafood.core.springdoc.PedidoFiltro;
import com.algaworks.algafood.core.springdoc.PedidoPageableParameter;
import com.algaworks.algafood.domain.filter.PedidoFilter;
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

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface PedidoControllerOpenApi {

    @PedidoFiltro
    @PedidoPageableParameter
    @Operation(summary = "Pesquisa os pedidos", description = "Pesquisa todos os pedidos", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(ref = "PedidoResumoModel")))
    })
    PagedModel<PedidoResumoModel> pesquisar(
            @Parameter(hidden = true) PedidoFilter filtro, @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Cadastra um novo pedido",
            description = "Cadastra um novo pedido informando uma representação válida", responses = {
            @ApiResponse(responseCode = "201", description = "Pedido criado")
    })
    PedidoModel adicionar(@RequestBody(description = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);

    @Operation(summary = "Busca um pedido por código", description = "Busca um pedido informando um código válido", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Código do pedido inválido",
                    content = @Content(schema = @Schema(ref = "Problema"))),
    })
    PedidoModel buscar(@Parameter(description = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55") String codigoPedido);

}