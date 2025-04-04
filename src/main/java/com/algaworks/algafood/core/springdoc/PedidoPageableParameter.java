package com.algaworks.algafood.core.springdoc;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(
        in = ParameterIn.QUERY,
        name = "page",
        description = "Número da página (0..N)",
        schema = @Schema(type = "integer", defaultValue = "0")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "size",
        description = "Quantidade de elementos por página",
        schema = @Schema(type = "integer", defaultValue = "10")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "sort",
        description = "Critério de ordenação: propriedade(asc|desc).",
        schema = @Schema(example = "taxaFrete"),
        examples = {
                @ExampleObject(name = "nomeCliente,desc"),
                @ExampleObject(name = "codigo"),
                @ExampleObject(name = "subtotal"),
                @ExampleObject(name = "taxaFrete"),
                @ExampleObject(name = "valorTotal"),
                @ExampleObject(name = "dataCriacao"),
                @ExampleObject(name = "nomeRestaurante"),
                @ExampleObject(name = "restauranteId,asc"),
                @ExampleObject(name = "clienteId,desc"),
        }
)
public @interface PedidoPageableParameter {
}
