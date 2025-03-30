package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;
import com.algaworks.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    @Override
    public CollectionModel<EstadoModel> listar() {

        return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    @Override
    public EstadoModel buscar(@PathVariable Long estadoId) {

        return estadoModelAssembler.toModel(cadastroEstadoService.buscarOuFalhar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        return estadoModelAssembler.toModel(cadastroEstadoService.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    @Override
    public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
       Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);
       estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
       return estadoModelAssembler.toModel(cadastroEstadoService.salvar(estadoAtual));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{estadoId}")
    @Override
    public ResponseEntity<Void> remover(@PathVariable Long estadoId){
        cadastroEstadoService.excluir(estadoId);
        return ResponseEntity.noContent().build();
    }

}
