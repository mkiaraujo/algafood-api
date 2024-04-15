package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;

import java.util.List;
import java.util.Optional;

public interface CozinhaRepository {

    List<Cozinha> listar();
    Optional<Cozinha> buscar(Long id);
    Optional<Cozinha> salvar(Cozinha cozinha);
    void remover(Cozinha cozinha);

}
