package com.algaworks.algafood.core.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("AlgaFood API")
//                        .version("v1")
//                        .description("REST API do Algafood")
//                        .license(new License()
//                                .name("Apache 2.0")
//                                .url("http://springdoc.com")
//                        )
//                ).externalDocs(new ExternalDocumentation()
//                        .description("AlgaWorks")
//                        .url("https://algaworks.com")
//                );
//
//
//    }

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
                    );
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
