package com.algaworks.algafood.core.springdoc;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(
        in = ParameterIn.QUERY,
        name = "clienteId",
        description = "ID do cliente",
        schema = @Schema(type = "Long", example = "1")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "restauranteId",
        description = "ID do restaurante",
        schema = @Schema(type = "Long", example = "1")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "dataCriacaoInicio",
        description = "Data inicial",
        schema = @Schema(type = "DateTime", example = "2019-10-30T21:10:00Z")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "dataCriacaoFim",
        description = "Data final",
        schema = @Schema(type = "DateTime", example = "2019-11-01T21:10:00Z")
)
public @interface PedidoFiltro {
}
