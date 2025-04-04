package com.algaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "FormaPagamentoInput")
public class FormaPagamentoInput {

    @Schema(example = "Cartão")
    @NotBlank
    private String descricao;
}
