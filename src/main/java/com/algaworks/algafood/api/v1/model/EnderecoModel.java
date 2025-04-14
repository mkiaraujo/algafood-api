package com.algaworks.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "EnderecoModel")
public class EnderecoModel {

    @Schema(example = "72996365")
    private String cep;

    @Schema(example = "Rua vicente pires")
    private String logradouro;

    @Schema(example = "9")
    private String numero;

    @Schema(example = "casa 9")
    private String complemento;

    @Schema(example = "Taguatinga")
    private String bairro;

    @Schema(description = "cidade")
    private CidadeModel cidade;

}
