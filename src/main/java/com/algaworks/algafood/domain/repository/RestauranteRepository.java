package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RestauranteRepository {

    List<Restaurante> listar();
    Optional<Restaurante> buscar(Long id);
    Optional<Restaurante> salvar(Restaurante restaurante);
    void remover(Restaurante restaurante);
}
