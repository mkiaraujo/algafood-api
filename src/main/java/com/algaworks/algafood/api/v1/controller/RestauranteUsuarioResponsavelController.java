package com.algaworks.algafood.api.v1.controller;


import com.algaworks.algafood.api.v1.Algalinks;
import com.algaworks.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private Algalinks algalinks;

    @GetMapping
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId){
        var restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        var usuariosModel = usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis())
                .removeLinks()
                .add(algalinks.linkToResponsaveisRestaurante(restauranteId))
                .add(algalinks.linkToRestauranteUsuarioResponsavelAssociar(restauranteId, "associar"));

        usuariosModel.getContent().forEach(
                usuarioModel -> usuarioModel.add(algalinks.linkToRestauranteUsuarioResponsavelDesassociar(
                        restauranteId, usuarioModel.getId(), "desassociar"
                ))
        );

        return usuariosModel;
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
        cadastroRestauranteService.associarUsuario(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.desassociarUsuario(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }


}
