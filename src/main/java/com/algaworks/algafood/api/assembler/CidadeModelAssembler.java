package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeModelAssembler {


    @Autowired
    private ModelMapper modelMapper;

    public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
        return cidades
                .stream()
                .map(cidade -> toModel(cidade)).collect(Collectors.toList());
    }

    public CidadeModel toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeModel.class);
    }
}
