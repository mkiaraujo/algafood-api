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
            description = "Cadastra um novo pedido informando uma representação válida")
    PedidoModel adicionar(PedidoInput pedidoInput);

    @Operation(summary = "Busca um pedido por ID", description = "Busca um pedido informando um ID válido")
    PedidoModel buscar(String codigoPedido);

}