package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FotoProdutoModelAssembler {


    @Autowired
    private ModelMapper modelMapper;

    public List<FotoProdutoModel> toCollectionModel(List<FotoProduto> fotoProdutos) {
        return fotoProdutos
                .stream()
                .map(fotoProduto -> toModel(fotoProduto)).collect(Collectors.toList());
    }

    public FotoProdutoModel toModel(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoProdutoModel.class);
    }
}
