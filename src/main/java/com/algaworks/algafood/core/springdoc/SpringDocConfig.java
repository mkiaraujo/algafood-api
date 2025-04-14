package com.algaworks.algafood.core.springdoc;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.*;
import com.algaworks.algafood.api.v1.model.input.*;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@SecurityScheme(name = "security_auth",
    type = SecuritySchemeType.OAUTH2,
    flows = @OAuthFlows(authorizationCode = @OAuthFlow(
            authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
            tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
            scopes = {
                    @OAuthScope(name = "READ", description = "read scope"),
                    @OAuthScope(name = "WRITE", description = "write scope"),

            }
    )))
public class SpringDocConfig {

    private static final String BAD_REQUEST_RESPONSE = "BadRequestResponse";
    private static final String NOT_FOUND_RESPONSE = "NotFoundResponse";
    private static final String NOT_ACCEPTABLE_RESPONSE = "NotAcceptableResponse";

    private static final String CONFLIT_RESPONSE = "ConflitResponse";
    private static final String INTERNAL_SERVER_ERROR_RESPONSE = "InternalServerErrorResponse";


    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("AlgaFood API V1")
                .pathsToMatch("/v1/**")
                .addOpenApiCustomizer( openApi -> {
                    openApi.info(new Info()
                            .title("AlgaFood API V1")
                            .version("v1")
                            .description("REST API do Algafood")
                            .license(new License()
                                    .name("Apache 2.0")
                                    .url("http://springdoc.com")
                            )
                    ).externalDocs(new ExternalDocumentation()
                            .description("AlgaWorks")
                            .url("https://algaworks.com")
                    ).tags(Arrays.asList(
                            new Tag().name("Cidades").description("Gerencia as cidades"),
                            new Tag().name("Usuários").description("Gerencia os usuários"),
                            new Tag().name("Restaurantes").description("Gerencia os restaurantes"),
                            new Tag().name("Produtos").description("Gerencia os produtos dos restaurantes"),
                            new Tag().name("Formas-pagamento-restaurante").description("Gerencia as formas de pagamento dos restaurantes"),
                            new Tag().name("Pedidos-restaurante").description("Gerencia os pedidos realizados aos restaurantes"),
                            new Tag().name("Grupos").description("Gerencia os grupos"),
                            new Tag().name("Grupo-Permissoes").description("Gerencia as permissões dos grupos"),
                            new Tag().name("Formas de pagamento").description("Gerencia as formas de pagamento"),
                            new Tag().name("Estados").description("Gerencia os estados"),
                            new Tag().name("Cozinhas").description("Gerencia as cozinhas"),
                            new Tag().name("Pedidos").description("Gerencia os pedidos"),
                            new Tag().name("Permissões").description("Gerencia as permissões"),
                            new Tag().name("Estatísticas").description("Gerencia as estatísticas de vendas"),
                            new Tag().name("Root-entry-point").description("Gerencia todos os links dos serviços da API")
                    )).components(new Components()
                            .schemas(gerarSchemas())
                            .responses(gerarResponses())
                    );
                })
                .addOpenApiCustomizer(openApi -> {
                    openApi.getPaths()
                            .values()
                            .forEach(pathItem -> pathItem.readOperationsMap()
                                    .forEach((httpMethod , operation) -> {
                                        var responses = operation.getResponses();
                                        switch (httpMethod){
                                            case GET -> {
                                                responses.addApiResponse("406",
                                                        new ApiResponse().$ref(NOT_ACCEPTABLE_RESPONSE));
                                                responses.addApiResponse("500",
                                                        new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                                            }
                                            case POST -> {
                                                responses.addApiResponse("400",
                                                        new ApiResponse().$ref(BAD_REQUEST_RESPONSE));
                                                responses.addApiResponse("500",
                                                        new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                                            }
                                            case PUT -> {
                                                responses.addApiResponse("400",
                                                        new ApiResponse().$ref(BAD_REQUEST_RESPONSE));
                                                responses.addApiResponse("500",
                                                        new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                                            }
                                            case DELETE -> {
                                                responses.addApiResponse("409",
                                                        new ApiResponse().$ref(CONFLIT_RESPONSE));
                                                responses.addApiResponse("500",
                                                        new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                                            }
                                            default -> {
                                                responses.addApiResponse("500",
                                                        new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                                            }

                                        }
                                    })
                            );

                })
//                .addOpenApiCustomizer(openApi -> openApi.components(new Components().schemas(gerarSchemas())))
                .build();
    }

    private Map<String, ApiResponse> gerarResponses() {
        final Map<String, ApiResponse> apiResponsesMap = new HashMap<>();

        Content content = new Content()
                .addMediaType(APPLICATION_JSON_VALUE,
                        new MediaType().schema(new Schema<Problem>().$ref("Problema")));

        apiResponsesMap.put(BAD_REQUEST_RESPONSE, new ApiResponse()
                .description("Requisição inválida")
                .content(content));

        apiResponsesMap.put(NOT_FOUND_RESPONSE, new ApiResponse()
                .description("Recurso não encontrado")
                .content(content));

        apiResponsesMap.put(NOT_ACCEPTABLE_RESPONSE, new ApiResponse()
                .description("Recurso não possui representação aceita pelo consumidor")
                .content(content));

        apiResponsesMap.put(CONFLIT_RESPONSE, new ApiResponse()
                .description("Recurso em uso")
                .content(content));

        apiResponsesMap.put(INTERNAL_SERVER_ERROR_RESPONSE, new ApiResponse()
                .description("Erro interno do servidor")
                .content(content));

        return apiResponsesMap;
    }

    private Map<String, Schema> gerarSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();

        var cidadeModelSchema = ModelConverters.getInstance().read(CidadeModel.class);
        var cidadeInputSchema = ModelConverters.getInstance().read(CidadeInput.class);
        var CidadeIdInputSchema = ModelConverters.getInstance().read(CidadeIdInput.class);

        var estadoModelSchema = ModelConverters.getInstance().read(EstadoModel.class);
        var estadoInputSchema = ModelConverters.getInstance().read(EstadoIdInput.class);

        var enderecoModelSchema = ModelConverters.getInstance().read(EnderecoModel.class);
        var enderecoInputSchema = ModelConverters.getInstance().read(EnderecoInput.class);

        var grupoModelSchema = ModelConverters.getInstance().read(GrupoModel.class);
        var grupoInputSchema = ModelConverters.getInstance().read(GrupoInput.class);


        var cozinhaModelSchema = ModelConverters.getInstance().read(CozinhaModel.class);
        var cozinhaInputSchema = ModelConverters.getInstance().read(CozinhaInput.class);

        var formaPagamentoModelSchema = ModelConverters.getInstance().read(FormaPagamentoModel.class);
        var formaPagamentoInputSchema = ModelConverters.getInstance().read(FormaPagamentoInput.class);
        var formaPagamentoIdInputSchema = ModelConverters.getInstance().read(FormaPagamentoIdInput.class);

        var pedidoResumoModelSchema = ModelConverters.getInstance().read(PedidoResumoModel.class);
        var pedidoModelSchema = ModelConverters.getInstance().read(PedidoModel.class);
        var itemPedidoModelSchema = ModelConverters.getInstance().read(ItemPedidoModel.class);
        var itemPedidoInputSchema = ModelConverters.getInstance().read(ItemPedidoInput.class);

        var pedidoInputSchema = ModelConverters.getInstance().read(PedidoInput.class);
        var restauranteIdInputSchema = ModelConverters.getInstance().read(RestauranteIdInput.class);

        var restauranteApenasNomeModelSchema = ModelConverters.getInstance().read(RestauranteApenasNomeModel.class);
        var usuarioModelSchema = ModelConverters.getInstance().read(UsuarioModel.class);


        var problemSchema = ModelConverters.getInstance().read(Problem.class);
        var problemObjectSchema = ModelConverters.getInstance().read(Problem.Object.class);


        schemaMap.putAll(problemSchema);
        schemaMap.putAll(problemObjectSchema);
        schemaMap.putAll(cidadeModelSchema);
        schemaMap.putAll(cidadeInputSchema);
        schemaMap.putAll(estadoModelSchema);
        schemaMap.putAll(estadoInputSchema);
        schemaMap.putAll(grupoModelSchema);
        schemaMap.putAll(grupoInputSchema);
        schemaMap.putAll(cozinhaModelSchema);
        schemaMap.putAll(cozinhaInputSchema);
        schemaMap.putAll(formaPagamentoModelSchema);
        schemaMap.putAll(formaPagamentoInputSchema);
        schemaMap.putAll(pedidoResumoModelSchema);
        schemaMap.putAll(restauranteApenasNomeModelSchema);
        schemaMap.putAll(usuarioModelSchema);
        schemaMap.putAll(enderecoModelSchema);
        schemaMap.putAll(pedidoModelSchema);
        schemaMap.putAll(itemPedidoModelSchema);
        schemaMap.putAll(CidadeIdInputSchema);
        schemaMap.putAll(enderecoInputSchema);
        schemaMap.putAll(formaPagamentoIdInputSchema);
        schemaMap.putAll(itemPedidoInputSchema);
        schemaMap.putAll(pedidoInputSchema);
        schemaMap.putAll(restauranteIdInputSchema);

        return schemaMap;
    }

    @Bean
    public GroupedOpenApi groupedOpenApiCliente() {
        return GroupedOpenApi.builder()
                .group("AlgaFood API V2")
                .pathsToMatch("/v2/**")
                .addOpenApiCustomizer( openApi -> {
                    openApi.info(new Info()
                            .title("AlgaFood API V2")
                            .version("v2")
                            .description("REST API do Algafood")
                            .license(new License()
                                    .name("Apache 2.0")
                                    .url("http://springdoc.com")
                            )
                    ).externalDocs(new ExternalDocumentation()
                            .description("AlgaWorks")
                            .url("https://algaworks.com")
                    );
                })
                .build();
    }
}
