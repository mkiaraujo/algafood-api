package com.algaworks.algafood;


import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestaurante {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    private static final Long RESTAURANTE_ID_INEXISTENTE = 100L;
    private static final String PATH_JSON_RESTAURANTE = "/json/restaurantes.json";
    private Long quantidadeDeRestaurantesCadastrados;

    private String listaDeRestaurantes;

    private Restaurante restauranteAleatorio;

    @BeforeEach
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        databaseCleaner.clearTables();
        prepararDados();

    }

    private void prepararDados() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Americana");
        cozinhaRepository.save(cozinha2);

        Cozinha cozinha3 = new Cozinha();
        cozinha3.setNome("Brasileira");
        cozinhaRepository.save(cozinha3);

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Tailandez");
        restaurante1.setTaxaFrete(BigDecimal.valueOf(10));
        Cozinha cozinhaNova = new Cozinha();
        cozinhaNova.setId(1L);
        restaurante1.setCozinha(cozinhaNova);
        restauranteRepository.save(restaurante1);



        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Americano");
        restaurante2.setTaxaFrete(BigDecimal.valueOf(10.10));
        cozinhaNova.setId(2L);
        restaurante2.setCozinha(cozinhaNova);
        restauranteRepository.save(restaurante2);

        quantidadeDeRestaurantesCadastrados = restauranteRepository.count();

        listaDeRestaurantes = ResourceUtils.getContentFromResource(PATH_JSON_RESTAURANTE);

        restauranteAleatorio = restauranteRepository.buscarPrimeiro().get();

    }

    //    TESTES DE INTEGRAÇÃO
    @Test
    public void deveAtribuirId_QuandoCadastrarRestauranteComDadosCorretos() {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Brasileiro");
        restaurante.setTaxaFrete(BigDecimal.valueOf(20.30));

        Cozinha cozinha = new Cozinha();
        cozinha.setId(3L);

        restaurante.setCozinha(cozinha);
        restaurante = restauranteRepository.save(restaurante);

        Assertions.assertThat(restaurante).isNotNull();
        Assertions.assertThat(restaurante.getId()).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoCadastrarRestauranteSemNome() {
        Restaurante novoRestaurante = new Restaurante();
        novoRestaurante.setNome(null);
        novoRestaurante.setTaxaFrete(BigDecimal.valueOf(30));

        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setId(1L);

        novoRestaurante.setCozinha(novaCozinha);

        ThrowableAssert.ThrowingCallable throwingCallable =
                () -> restauranteRepository.save(novoRestaurante);

        Assertions.assertThatThrownBy(throwingCallable)
                .isInstanceOf(ConstraintViolationException.class);


    }

    @Test
    public void deveFalhar_QuandoCadastrarRestauranteSemIdDaCozinha() {
        Restaurante novoRestaurante = new Restaurante();
        novoRestaurante.setNome("Brasiliense");
        novoRestaurante.setTaxaFrete(BigDecimal.valueOf(30));

        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setId(null);

        novoRestaurante.setCozinha(novaCozinha);

        ThrowableAssert.ThrowingCallable throwingCallable =
                () -> restauranteRepository.save(novoRestaurante);

        Assertions.assertThatThrownBy(throwingCallable)
                .isInstanceOf(InvalidDataAccessApiUsageException.class);

    }

    @Test
    public void deveFalhar_QuandoTentarExcluirRestauranteInexistente() {

        Assertions
                .assertThatThrownBy(() -> cadastroRestauranteService
                        .excluir(RESTAURANTE_ID_INEXISTENTE))
                .isInstanceOf(RestauranteNaoEncontradoException.class);

    }

    //    FIM TESTES DE INTEGRAÇÃO

//    TESTES DE API

    @Test
    public void deveRetornarCodigo200_QuandoConsultarRestauranteExistente(){

        RestAssured
                .given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .statusCode(HttpStatus.OK.value());


    }

    @Test
    public void  deveConterTodosOsRestaurantes_QuandoConsultarRestaurantes(){
        RestAssured
                .given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .body("", Matchers.hasSize(quantidadeDeRestaurantesCadastrados.intValue()));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarRestaurante() {
        RestAssured
                .given()
                    .body(listaDeRestaurantes)
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                .when()
                    .post()
                .then()
                    .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatus200_QuandoConsultarRestauranteExistente() {
        RestAssured
                .given()
                    .pathParam("restauranteId", restauranteAleatorio.getId())
                    .accept(ContentType.JSON)
                .when()
                    .get("/{restauranteId}")
                .then()
                    .body("nome", Matchers.equalTo(restauranteAleatorio.getNome()));

    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarRestauranteInexistente() {
        RestAssured
                .given()
                    .pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
                    .accept(ContentType.JSON)
                .when()
                    .get("/{restauranteId}")
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarRestauranteComFreteGratis() {
        RestAssured
                .given()
                    .body(listaDeRestaurantes)
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                .when()
                    .post()
                .then()
                    .statusCode(HttpStatus.CREATED.value());
    }
}
