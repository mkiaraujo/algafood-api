package com.algaworks.algafood;

import static org.hamcrest.Matchers.*;

import com.algaworks.algafood.domain.exception.CozinhaEmUsoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
//import org.hamcrest.Matcher;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        flyway.migrate();
    }

//    TESTES DE INTEGRAÇÃO
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

//    FIM TESTES DE INTEGRAÇÃO

//    TESTES DE API

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {

        RestAssured
                .given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .statusCode(HttpStatus.OK.value());
    }
     @Test
     public void deveConter4Cozinhas_QuandoConsultarCozinhas() {

            RestAssured
                    .given()
                        .accept(ContentType.JSON)
                    .when()
                        .get()
                    .then()
                        .body("", hasSize(4))
                        .body("nome", hasItems("Indiana", "Tailandesa"));
     }

     @Test
     public void deveRetornarStatus201_QundoCadastrarCozinha() {
        RestAssured
                .given()
                    .body("{\"nome\": \"Chinesa\" }")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());

     }

//    FIM TESTES DE API
}
