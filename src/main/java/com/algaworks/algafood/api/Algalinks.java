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

    public Link linkToConfirmacaoPedido(String codigoPedido, String rel){
        return linkTo(methodOn(FluxoPedidoController.class).confirmar(codigoPedido)).withRel(rel);
    }

    public Link linkToEntregaPedido(String codigoPedido, String rel){
        return linkTo(methodOn(FluxoPedidoController.class).entregar(codigoPedido)).withRel(rel);
    }

    public Link linkToCancelamentoPedido(String codigoPedido, String rel){
        return linkTo(methodOn(FluxoPedidoController.class).cancelar(codigoPedido)).withRel(rel);
    }

    public Link linkToRestaurante(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class)
                .buscar(restauranteId)).withRel(rel);
    }
    public Link linkToRestaurante(Long restauranteId) {
        return linkToRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
    }
    public Link linkToRestaurantes(String rel) {
        return linkTo(RestauranteController.class).withRel(rel);
    }
    public Link linkToRestaurantes() {
        return linkToRestaurantes(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestauranteFormasPagamento(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class)
                .listar(restauranteId)).withRel(rel);
    }

    public Link linkToUsuario(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioController.class)
                .buscar(usuarioId)).withRel(rel);
    }
    public Link linkToUsuario(Long usuarioId) {
        return linkToUsuario(usuarioId, IanaLinkRelations.SELF_VALUE);
    }
    public Link linkToUsuarios(String rel) {
        return linkTo(UsuarioController.class).withRel(rel);
    }
    public Link linkToUsuarios() {
        return linkToUsuarios(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToGrupoUsuario(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId))
                .withRel(rel);
    }
    public Link linkToGrupoUsuario(Long usuarioId) {
        return linkToGrupoUsuario(usuarioId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToGrupoUsuarios(String rel) {
        return linkTo(UsuarioGrupoController.class).withRel(rel);
    }
    public Link linkToGrupoUsuarios() {
        return linkToUsuarios(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToGrupo(String rel) {
        return linkTo(GrupoController.class).withRel(rel);
    }
    public Link linkToGrupo() {
        return linkToUsuarios(IanaLinkRelations.SELF_VALUE);
    }


    public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
                .listar(restauranteId)).withRel(rel);
    }
    public Link linkToResponsaveisRestaurante(Long restauranteId) {
        return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToFormaPagamento(Long formaPagamentoId, String rel){
        return linkTo(methodOn(FormaPagamentoController.class)
                .buscar(formaPagamentoId, null)).withRel(rel);
    }

    public Link linkToFormaPagamento(Long formaPagamentoId){
        return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCidade(Long cidadeId, String rel){
        return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
    }

    public Link linkToCidade(Long cidadeId){
        return linkToCidade(cidadeId, IanaLinkRelations.SELF_VALUE);
    }
    public Link linkToCidades(String rel){
        return linkTo(CidadeController.class).withRel(rel);
    }
    public Link linkToCidades(){
        return linkToCidades(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToEstado(Long estadoId, String rel) {
        return linkTo(methodOn(EstadoController.class)
                .buscar(estadoId)).withRel(rel);
    }
    public Link linkToEstado(Long estadoId) {
        return linkToEstado(estadoId, IanaLinkRelations.SELF_VALUE);
    }
    public Link linkToEstados(String rel) {
        return linkTo(EstadoController.class).withRel(rel);
    }
    public Link linkToEstados() {
        return linkToEstados(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToProduto(Long restauranteId, Long produtoId, String rel) {
        return linkTo(methodOn(RestauranteProdutoController.class)
                .buscar(restauranteId, produtoId))
                .withRel(rel);
    }
    public Link linkToProduto(Long restauranteId, Long produtoId) {
        return linkToProduto(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCozinhas(String rel){
        return linkTo(CozinhaController.class).withRel(rel);
    }

    public Link linkToCozinhas() {
        return linkToCozinhas(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCozinha(Long cozinhaId, String rel) {
        return linkTo(methodOn(CozinhaController.class)
                .buscar(cozinhaId)).withRel(rel);
    }
    public Link linkToCozinha(Long cozinhaId) {
        return linkToCozinha(cozinhaId, IanaLinkRelations.SELF_VALUE);
    }



}
