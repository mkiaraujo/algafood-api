package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;

import java.util.List;
import java.util.Optional;


public interface RestauranteRepository {

    List<Restaurante> listar();
    Optional<Restaurante> buscar(Long id);
    Optional<Restaurante> salvar(Restaurante restaurante);
    void remover(Restaurante restaurante);
}
