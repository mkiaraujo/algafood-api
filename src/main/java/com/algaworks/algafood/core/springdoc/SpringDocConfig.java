package com.algaworks.algafood.core.springdoc;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

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
                            new Tag().name("Grupos").description("Gerencia os grupos e suas permissões"),
                            new Tag().name("Formas-pagamento").description("Gerencia as formas de pagamento"),
                            new Tag().name("Estados").description("Gerencia os estados"),
                            new Tag().name("Cozinhas").description("Gerencia as cozinhas"),
                            new Tag().name("Pedidos").description("Gerencia os pedidos"),
                            new Tag().name("Permissões").description("Gerencia as permissões"),
                            new Tag().name("Estatísticas").description("Gerencia as estatísticas de vendas"),
                            new Tag().name("Root-entry-point").description("Gerencia todos os links dos serviços da API")
                    ));
                })
                .addOpenApiCustomizer(openApi -> {
                    openApi.getPaths()
                            .values()
                            .stream()
                            .flatMap(pathItem -> pathItem.readOperations().stream())
                            .forEach(operation -> {
                                var responses = operation.getResponses();

                                var apiResponseRecursoNaoEncontrado =
                                        new ApiResponse().description("Recurso não encontrado");

                                var apiResponseSemRepresentacao =
                                        new ApiResponse().description("Recurso não possui uma representação que " +
                                                "poderia ser aceita pelo consumidor");

                                var apiResponseErroInterno =
                                        new ApiResponse().description("Erro interno no servidor");

                                responses.addApiResponse("404", apiResponseRecursoNaoEncontrado);
                                responses.addApiResponse("406", apiResponseSemRepresentacao);
                                responses.addApiResponse("500", apiResponseErroInterno);

                            });
                })
                .build();
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
