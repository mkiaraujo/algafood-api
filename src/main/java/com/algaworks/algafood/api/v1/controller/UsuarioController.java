package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping
    public CollectionModel<UsuarioModel> listar(){
        var usuarios =  usuarioRepository.findAll();

        return usuarioModelAssembler.toCollectionModel(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId){
        return usuarioModelAssembler.toModel(cadastroUsuarioService.buscarOuFalhar(usuarioId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput){
            var usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
            return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
                                  @RequestBody @Valid UsuarioInput usuarioInput){
        var usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuario);
        return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuario));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{usuarioId}/senha")
    public void alterarSenha(@PathVariable Long usuarioId,
                               @RequestBody @Valid SenhaInput senha){
        cadastroUsuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }

}
