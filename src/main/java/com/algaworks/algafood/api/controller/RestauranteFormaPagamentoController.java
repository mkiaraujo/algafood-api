package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.Algalinks;
import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private Algalinks algalinks;

    @GetMapping
    public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        var formasPagamentoModel = formaPagamentoModelAssembler
                .toCollectionModel(restaurante.getFormasPagamento())
                .removeLinks()
                .add(algalinks.linkToRestauranteFormasPagamento(restauranteId))
                .add(algalinks.linkToRestauranteFormaPagamentoAssociacao(restauranteId, "associar"));


        formasPagamentoModel.getContent().forEach(formaPagamentoModel -> {
            formasPagamentoModel.add(
                    algalinks
                            .linkToRestauranteFormaPagamentoDesassociacao(
                                    restauranteId, formaPagamentoModel.getId(), "desassociar"));
        });

        return formasPagamentoModel;
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }




}
