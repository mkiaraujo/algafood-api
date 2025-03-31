package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@Schema(name = "Problema")
public class Problem {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "2025-03-31T14:12:00Z")
    private OffsetDateTime timestamp;

    @Schema(example = "https://algafood.com.br/dados-invalidos")
    private String type;

    @Schema(example = "Dados inválidos")
    private String title;

    @Schema(example = "Um ou mais campos estão inválidos. Faça o prenchimento correto e tente novamente.")
    private String detail;

    @Schema(example = "Um ou mais campos estão inválidos. Faça o prenchimento correto e tente novamente.")
    private String userMessage;

    @Schema(description = "Lista de objetos ou campos que geraram o erro")
    private List<Object> objects;

    @Getter
    @Builder
    @Schema(name = "Objetoproblema")
    public static class Object {

        @Schema(example = "preço")
        private String name;

        @Schema(example = "O preço é inválido")
        private String userMessage;
    }


}
