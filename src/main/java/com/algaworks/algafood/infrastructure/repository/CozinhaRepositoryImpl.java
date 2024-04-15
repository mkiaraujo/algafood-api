package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Override
    public List<Cozinha> consultarPorNome(String nome) {
        return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public Optional<Cozinha> buscar(Long id) {
        return Optional.ofNullable(manager.find(Cozinha.class, id));
    }

    @Transactional
    @Override
    public Optional<Cozinha> salvar(Cozinha cozinha) {
        return Optional.of(manager.merge(cozinha));
    }

    @Transactional
    @Override
    public void remover(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId()).get();
        manager.remove(cozinha);
    }
}
