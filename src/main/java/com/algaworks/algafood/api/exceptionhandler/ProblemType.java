package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }
}
