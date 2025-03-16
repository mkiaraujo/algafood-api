package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private Algalinks algalinks;

    @GetMapping
    public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId){
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        var gruposModel =  grupoModelAssembler.toCollectionModel(usuario.getGrupos())
               .removeLinks()
               .add(algalinks.linkToGrupoUsuarioAssociar(usuarioId, "associar"));

        gruposModel.getContent().forEach(grupoModel ->
                grupoModel.add(algalinks.linkToGrupoUsuarioDesassociar(
                        usuarioId,
                        grupoModel.getId(),
                        "desassociar")));

        return gruposModel;
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void>  desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

}
