package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaEmUsoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CadastroCozinhaIT {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
//        cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");
//        ação
        novaCozinha = cadastroCozinha.salvar(novaCozinha);
//        validação
        Assertions.assertThat(novaCozinha).isNotNull();
        Assertions.assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test//(expected = ConstraintViolationException.class)
    public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
        final Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        Assertions.assertThatThrownBy(() -> cadastroCozinha.salvar(novaCozinha))
                .isInstanceOf(ConstraintViolationException.class);
//        novaCozinha = cadastroCozinha.salvar(novaCozinha);

    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
        Long id = 1L;

        Assertions.assertThatThrownBy(() -> cadastroCozinha.excluir(id))
                .isInstanceOf(CozinhaEmUsoException.class);

    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {
        Long id = 10L;

        Assertions.assertThatThrownBy(() -> cadastroCozinha.excluir(id))
                .isInstanceOf(CozinhaNaoEncontradaException.class);
    }
}
