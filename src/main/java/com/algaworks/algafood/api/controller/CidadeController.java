package com.algaworks.algafood.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

    @SuppressWarnings("unused")
    @Autowired
    private CidadeRepository cidadeRepository;

    @SuppressWarnings("unused")
    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public CollectionModel<CidadeModel> listar() {
        var todasCidades = cidadeRepository.findAll();

        var cidadesModel = cidadeModelAssembler.toCollectionModel(todasCidades);

        cidadesModel.forEach(cidadeModel -> {
            cidadeModel.add(linkTo(methodOn(CidadeController.class)
                    .buscar(cidadeModel.getId())).withSelfRel());

            cidadeModel.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));

            cidadeModel
                    .getEstado()
                    .add(linkTo(methodOn(EstadoController.class)
                            .buscar(cidadeModel.getEstado().getId())).withSelfRel());
        });

        var cidadesCollectionModel = CollectionModel.of(cidadesModel);

        cidadesCollectionModel.add(linkTo(methodOn(CidadeController.class)
                .listar()).withSelfRel());

        return cidadesCollectionModel;
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        var cidadeModel = cidadeModelAssembler.toModel(cadastroCidadeService.buscarOuFalhar(cidadeId));

        cidadeModel.add(linkTo(methodOn(CidadeController.class)
                .buscar(cidadeModel.getId())).withSelfRel());

        cidadeModel.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));

        cidadeModel
                .getEstado()
                        .add(linkTo(methodOn(EstadoController.class)
                                .buscar(cidadeModel.getEstado().getId())).withSelfRel());

        return cidadeModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput){
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            var cidadeModel =  cidadeModelAssembler.toModel(cadastroCidadeService.salvar(cidade));

            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());

            return cidadeModel;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);
            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidade);
            return cidadeModelAssembler.toModel(cadastroCidadeService.salvar(cidade));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId){
        cadastroCidadeService.excluir(cidadeId);
    }

}
