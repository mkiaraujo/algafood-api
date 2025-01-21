package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.ProdutoDoRestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    @Transactional
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }


    public Produto buscarOuFalharProdutoDoRestaurante(Long restauranteId, Long produtoId) {
        return produtoRepository
                .findByIdWhereRestauranteById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoDoRestauranteNaoEncontradoException(restauranteId, produtoId));
    }

    public Produto buscarOuFalhar(Long produtoId) {
        return produtoRepository
                .findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }
}
