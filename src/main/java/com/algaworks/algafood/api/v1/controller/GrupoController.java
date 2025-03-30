package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import com.algaworks.algafood.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;


    @GetMapping
    @Override
    public CollectionModel<GrupoModel> listar(){
        return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());

    }


    @GetMapping("/{grupoId}")
    @Override
    public GrupoModel buscar(@PathVariable Long grupoId){
        return grupoModelAssembler.toModel(cadastroGrupoService.buscarOuFalhar(grupoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput){
        var grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{grupoId}")
    @Override
    public ResponseEntity<Void> remover(@PathVariable Long grupoId){
        cadastroGrupoService.excluir(grupoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{grupoId}")
    @Override
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        var grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
        grupoInputDisassembler.copyToDomainObject(grupoInput, grupo);
        return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }
}
