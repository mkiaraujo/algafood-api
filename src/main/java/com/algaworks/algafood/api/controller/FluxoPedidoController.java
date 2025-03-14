package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedidos/{codigoPedido}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxoPedidoService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirmar(@PathVariable String codigoPedido){
        fluxoPedidoService.confirmar(codigoPedido);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/entregue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> entregar(@PathVariable String codigoPedido){
        fluxoPedidoService.entregar(codigoPedido);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelar(@PathVariable String codigoPedido){
        fluxoPedidoService.cancelar(codigoPedido);

        return ResponseEntity.noContent().build();
    }

}
