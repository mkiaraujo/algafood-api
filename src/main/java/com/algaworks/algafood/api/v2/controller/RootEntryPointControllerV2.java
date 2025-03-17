package com.algaworks.algafood.api.v2.controller;

import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v2.AlgalinksV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v2", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointControllerV2 {

    @Autowired
    private AlgalinksV2 algalinks;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(algalinks.linkToCidades("cidades"));

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }

}
