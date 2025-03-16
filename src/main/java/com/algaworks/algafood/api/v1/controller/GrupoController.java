package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;


    @GetMapping
    public CollectionModel<GrupoModel> listar(){
        return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());

    }


    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId){
        return grupoModelAssembler.toModel(cadastroGrupoService.buscarOuFalhar(grupoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput){
        var grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{grupoId}")
    public void remover(@PathVariable Long grupoId){
        cadastroGrupoService.excluir(grupoId);
    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        var grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
        grupoInputDisassembler.copyToDomainObject(grupoInput, grupo);
        return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }
}
