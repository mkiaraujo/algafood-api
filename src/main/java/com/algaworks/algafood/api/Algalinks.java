package com.algaworks.algafood.api;

import com.algaworks.algafood.api.controller.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class Algalinks {

    private static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
    );

    public Link linkToPedidos(){

        var filtroVariables = new TemplateVariables(
                new TemplateVariable("clienteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoInicio", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoFim", TemplateVariable.VariableType.REQUEST_PARAM)
        );


        var pedidosUrl = linkTo(PedidoController.class).toUri().toString();

        return Link.of(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(filtroVariables)),
                "pedidos");
    }

    public Link linkToFormaPagamento(Long formaPagamentoId){
        return linkTo(methodOn(FormaPagamentoController.class)
                .buscar(formaPagamentoId, null)).withSelfRel();
    }

    public Link linkToRestaurante(Long restauranteId) {
        return linkTo(methodOn(RestauranteController.class)
                .buscar(restauranteId)).withSelfRel();
    }

    public Link linkToUsuario(Long clienteId) {
        return linkTo(methodOn(UsuarioController.class)
                .buscar(clienteId)).withSelfRel();
    }

    public Link linkToEnderecoEntrega(Long cidadeId ) {
        return linkTo(methodOn(CidadeController.class)
                .buscar(cidadeId)).withSelfRel();
    }

    public Link linkToItem(Long restauranteId, Long produtoId) {
        return linkTo(methodOn(RestauranteProdutoController.class)
                .buscar(restauranteId, produtoId)).withSelfRel();
    }

    public Link linkToCidade(String rel){
        return linkTo(CidadeController.class).withRel(rel);
    }
    public Link linkToCidade(){
        return linkToCidade("cidades");
    }

    public Link linkToEstado(Long estadoId) {
        return linkTo(methodOn(EstadoController.class)
                .buscar(estadoId)).withSelfRel();
    }

    public Link linkToEstado(String rel) {
        return linkTo(EstadoController.class).withRel(rel);
    }


    public Link linkToCozinha(String rel){
        return linkTo(CozinhaController.class).withRel(rel);
    }

    public Link linkToCozinha() {
        return linkToCozinha("cozinhas");
    }

    public Link linkToGrupo(String rel) {
        return linkTo(GrupoController.class).withRel(rel);
    }
    public Link linkToGrupo() {
        return linkToGrupo("grupos");
    }

    public Link linkToUsuario(String  rel) {
        return linkTo(UsuarioController.class)
                .withRel(rel);
    }
    public Link linkToUsuario() {
            return linkToUsuario("usuarios");
    }

    public Link linkToUsuarioGrupo(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId))
                .withRel(rel);
    }





}
