package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listar() {
        return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    public Optional<Cidade> buscar(Long id) {
        return Optional.ofNullable(manager.find(Cidade.class, id));
    }

    @Transactional
    @Override
    public Optional<Cidade> salvar(Cidade cidade) {
        return Optional.ofNullable(manager.merge(cidade));
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Optional<Cidade> cidade = buscar(id);
        if (!cidade.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cidade.get());
    }
}
