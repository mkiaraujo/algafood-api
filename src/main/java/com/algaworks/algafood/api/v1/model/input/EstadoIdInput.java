package com.algaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "EstadoIdInput")
public class EstadoIdInput {

    @Schema(example = "1")
    @NotNull
    private Long id;
}
