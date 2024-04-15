package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository {

    List<Cidade> listar();
    Optional<Cidade> buscar(Long id);
    Optional<Cidade> salvar(Cidade cidade);
    void remover(Long id);
}
