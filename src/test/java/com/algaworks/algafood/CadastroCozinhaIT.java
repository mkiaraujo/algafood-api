package com.algaworks.algafood;

import static org.hamcrest.Matchers.*;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
//import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import com.algaworks.algafood.util.DatabaseCleaner;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    private Long quantidadeCozinhasCadastradas;
    private String listaDeCozinhas;
    private static final String PATH_JSON_COZINHA = "/json/cozinhas.json";
    private Cozinha cozinhaAleatoria;
    private static final int COZINHA_ID_INEXISTENTE = 100;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        databaseCleaner.clearTables();
        prepararDados();


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
     public void deveConterTodasCozinhas_QuandoConsultarCozinhas() {

            RestAssured
                    .given()
                        .accept(ContentType.JSON)
                    .when()
                        .get()
                    .then()
                        .body("", hasSize(quantidadeCozinhasCadastradas.intValue()));
//                        .body("nome", hasItems("Americana", "Tailandesa"));
     }

     @Test
     public void deveRetornarStatus201_QundoCadastrarCozinha() {
        RestAssured
                .given()
                    .body(listaDeCozinhas)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .post()
                .then()
                    .statusCode(HttpStatus.CREATED.value());

     }


    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
        RestAssured
                .given()
                    .pathParam("cozinhaId", cozinhaAleatoria.getId())
                    .accept(ContentType.JSON)
                .when()
                    .get("/{cozinhaId}")
                .then()
                    .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(cozinhaAleatoria.getNome()));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
        RestAssured
                .given()
                    .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
                    .accept(ContentType.JSON)
                .when()
                    .get("/{cozinhaId}")
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
    }

//    FIM TESTES DE API

//    PREPARANDO A MASSA DE DADOS
    private void prepararDados() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Americana");
        cozinhaRepository.save(cozinha2);

        quantidadeCozinhasCadastradas = cozinhaRepository.count();

        listaDeCozinhas = ResourceUtils.getContentFromResource(PATH_JSON_COZINHA);

        cozinhaAleatoria = cozinhaRepository.buscarPrimeiro().get();




    }
}
