package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Optional<Estado> buscar(Long id) {
        return Optional.ofNullable(manager.find(Estado.class, id));
    }

    @Transactional
    @Override
    public Optional<Estado> salvar(Estado estado) {
        return Optional.ofNullable(manager.merge(estado));
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Optional<Estado> estado = buscar(id);
        if (!estado.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(estado.get());
    }
}
