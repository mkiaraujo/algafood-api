package com.algaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "EnderecoInput")
public class EnderecoInput {

    @NotBlank
    @Schema(example = "72996365")
    private String cep;

    @NotBlank
    @Schema(example = "Setor Vicente Pires")
    private String logradouro;

    @NotBlank
    @Schema(example = "9")
    private String numero;

    @Schema(example = "Casa 9")
    private String complemento;

    @NotBlank
    @Schema(example = "Taguatinga")
    private String bairro;

    @Valid
    @NotNull
    @Schema(description = "cidade")
    private CidadeIdInput cidade;
}
