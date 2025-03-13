package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.Algalinks;
import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private Algalinks algalinks;

    @GetMapping
    public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
        var permissoesModel = permissaoModelAssembler
                .toCollectionModel(grupo.getPermissoes())
                .removeLinks()
                .add(algalinks.linkToGrupoPermissoes(grupoId))
                .add(algalinks.linkToGrupoPermissoeAssociar(grupoId, "associar"));

        permissoesModel.getContent().forEach(permissaoModel -> {
            permissaoModel.add(algalinks.linkToGrupoPermissoeDesassociar(
                    grupoId,
                    permissaoModel.getId(),
                    "desassociar"));
        });

        return permissoesModel;

    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        cadastroGrupoService.associarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }
}
