package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CozinhaRepository {

    List<Cozinha> listar();
    List<Cozinha> consultarPorNome(String nome);
    Optional<Cozinha> buscar(Long id);
    Optional<Cozinha> salvar(Cozinha cozinha);
    void remover(Cozinha cozinha);

}
