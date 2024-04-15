package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Permissao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PermissaoRepository {

    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao salvar(Permissao permissao);
    void remover(Permissao permissao);
}
