package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

//        Erro ao adicionar uma nova cidade. Ele está identificando o idEstado como se fosse o id da cidade
//                atualizando a cidade e não criando uma nova cidade.
//        modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
//                .addMappings(mapper -> mapper.skip(Cidade::setId));

//        modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//                .addMapping(Restaurante::getTaxaFrete,RestauranteModel::setPrecoFrete);

//        var enderecoToEnderecoModelTypeMap = modelMapper
//                .createTypeMap(Endereco.class, EnderecoModel.class);
//
//        enderecoToEnderecoModelTypeMap
//                .<String>addMapping(
//                        enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
//                        (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

        return modelMapper;
    }
}
