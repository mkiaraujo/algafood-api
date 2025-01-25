package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.RestauranteEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CadastrarFormaPagamentoService cadastrarFormaPagamentoService;

    @Autowired
    private CadastrarProdutoService cadastrarProdutoService;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
        restaurante.setCozinha(cozinha);

        Long cidadeId = restaurante.getEndereco().getCidade().getId();
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Long restauranteId){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.ativar();
    }
    @Transactional
    public void inativar(Long restauranteId){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.inativar();
    }

    @Transactional
    public void excluir( Long restauranteId) {
        try {
            buscarOuFalhar(restauranteId);
            restauranteRepository.deleteById(restauranteId);
            restauranteRepository.flush();
        } catch (NoSuchElementException e) {
            throw new RestauranteNaoEncontradoException(restauranteId);
        } catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradoException(restauranteId);
        } catch (DataIntegrityViolationException e) {
            throw new RestauranteEmUsoException(restauranteId);
        }
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId){
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastrarFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);
    }
    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId){
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastrarFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.fechar();
    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.abrir();
    }

    @Transactional
    public void associarUsuario(Long restauranteId , Long usuarioId) {
        var restaurante = buscarOuFalhar(restauranteId);
        var usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        restaurante.adicionarUsuario(usuario);
    }

    @Transactional
    public void desassociarUsuario(Long restauranteId, Long usuarioId){
        var restaurante = buscarOuFalhar(restauranteId);
        var usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        restaurante.removerUsuario(usuario);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }
}
