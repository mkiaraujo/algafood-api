package com.algaworks.algafood.infrastructure.repository.spec;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.filter.PedidoFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;


public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            root.fetch("restaurante");
            root.fetch("cliente");
            root.fetch("restaurante").fetch("cozinha");
            root.fetch("restaurante")
                    .fetch("endereco")
                    .fetch("cidade")
                    .fetch("estado");

            // adicionar predicates no arraylist
            if (filtro.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente").get("id"), filtro.getClienteId()));
            }

            if (filtro.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante").get("id"), filtro.getRestauranteId()));
            }

            if (filtro.getDataCriacaoInicio() != null) {
                            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                                    filtro.getDataCriacaoInicio()));
            }

            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                        filtro.getDataCriacaoFim()));
            }
            var teste = builder.and(predicates.toArray(new Predicate[0]));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
