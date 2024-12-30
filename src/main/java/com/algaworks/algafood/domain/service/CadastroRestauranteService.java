package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CadastroRestauranteService {

    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Restaurante de código %d não foi encontrado";
    public static final String MSG_RESTAURANTE_ESTA_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso";
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Supplier<EntidadeNaoEncontradaException> exceptionSupplier = () ->  new EntidadeNaoEncontradaException(
                String.format("Não existe cadastro de cozinha com código %d", cozinhaId));

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(exceptionSupplier);

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public void excluir( Long restauranteId) {
        try {
            restauranteRepository.deleteById(restauranteId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_ESTADO_NAO_ENCONTRADO, restauranteId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_RESTAURANTE_ESTA_EM_USO , restauranteId)
            );
        }
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_ESTADO_NAO_ENCONTRADO, restauranteId)));
    }
}
