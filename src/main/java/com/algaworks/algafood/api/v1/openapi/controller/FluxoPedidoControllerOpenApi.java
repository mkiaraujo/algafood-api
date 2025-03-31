package com.algaworks.algafood.api.v1.openapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos-restaurante")
public interface FluxoPedidoControllerOpenApi {

    ResponseEntity<Void> confirmar(String codigoPedido);

    ResponseEntity<Void> cancelar(String codigoPedido);

    ResponseEntity<Void> entregar(String codigoPedido);

}