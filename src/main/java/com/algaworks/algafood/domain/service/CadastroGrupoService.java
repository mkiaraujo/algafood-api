package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.GrupoEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.RestauranteEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class CadastroGrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastrarPermissaoService cadastrarPermissaoService;

    public Grupo buscarOuFalhar(Long grupoId){
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long grupoId) {
            try {
                buscarOuFalhar(grupoId);
                grupoRepository.deleteById(grupoId);
                grupoRepository.flush();
            } catch (NoSuchElementException e) {
                throw new GrupoNaoEncontradoException(grupoId);
            } catch (EmptyResultDataAccessException e) {
                throw new GrupoNaoEncontradoException(grupoId);
            } catch (DataIntegrityViolationException e) {
                throw new GrupoEmUsoException(grupoId);
            }
    }

    @Transactional
    public void associarPermissao(Long grupoId , Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastrarPermissaoService.buscarOuFalhar(permissaoId);
        grupo.adicionarPermissao(permissao);
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastrarPermissaoService.buscarOuFalhar(permissaoId);

        grupo.removerPermissao(permissao);
    }
}
