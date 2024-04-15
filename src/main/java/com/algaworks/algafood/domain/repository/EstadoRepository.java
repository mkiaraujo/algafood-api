package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Estado;

import java.util.List;
import java.util.Optional;

public interface EstadoRepository {

    List<Estado> listar();
    Optional<Estado> buscar(Long id);
    Optional<Estado> salvar(Estado estado);
    void remover(Long id);
}
