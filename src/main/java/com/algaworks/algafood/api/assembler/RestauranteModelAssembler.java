package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.Algalinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Algalinks algalinks;

    public RestauranteModelAssembler() {
        super(RestauranteController.class , RestauranteModel.class);
    }

    @Override
    public RestauranteModel toModel(Restaurante restaurante) {
        var restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);

        restauranteModel.add(algalinks.linkToRestaurantes("restaurantes"));

        if (restaurante.ativacaoPermitida()){
            restauranteModel.add(algalinks.linkToAtivarRestaurante(restaurante.getId(), "ativar"));
        }

        if (restaurante.inativacaoPermitida()){
            restauranteModel.add(algalinks.linkToInativarRestaurante(restaurante.getId() , "inativar"));
        }

        if (restaurante.aberturaPermitida()){
            restauranteModel.add(algalinks.linkToAbrirRestaurante(restaurante.getId() , "abrir"));
        }

        if (restaurante.fechamentoPermitido()){
            restauranteModel.add(algalinks.linkToFecharRestaurante(restaurante.getId() , "fechar"));
        }

        restauranteModel.add(algalinks.linkToProdutos(restaurante.getId(), "produtos"));

        restauranteModel.getCozinha()
                .add(algalinks.linkToCozinha(restaurante.getCozinha().getId()));

        if (restauranteModel.getEndereco() != null
                    && restauranteModel.getEndereco().getCidade() != null){
            restauranteModel.getEndereco().getCidade()
                    .add(algalinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        }

        restauranteModel
                .add(algalinks.linkToRestauranteFormasPagamento(restaurante.getId(), "formas-pagamento"));

        restauranteModel
                .add(algalinks.linkToResponsaveisRestaurante(restaurante.getId(), "responsaveis"));

        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(algalinks.linkToRestaurantes());
    }

    public RestauranteInput toInputModel(Restaurante restauranteAtual) {
       return modelMapper.map(restauranteAtual, RestauranteInput.class);
    }

}
