package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.ProdutoDoRestauranteNaoEncontradoException;
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


    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository
                .findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoDoRestauranteNaoEncontradoException(restauranteId, produtoId));
    }
}
