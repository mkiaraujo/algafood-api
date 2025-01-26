package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository
        extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
        JpaSpecificationExecutor<Restaurante> {


    @Query("from Restaurante r " +
            "left join fetch r.cozinha " +
            "left join fetch r.endereco.cidade " +
            "left join fetch r.endereco.cidade.estado")
    List<Restaurante> findAll();

//    @Query("from Restaurante where restaurante.id := restauranteId and id := formaPagamentoId")
//    Optional<Restaurante> findByIdRestauranteAndFormaPagamento(Long restauranteId , Long formaPagamentoId);

//    List<Restaurante> findAll();

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

//    @Query("from Restaurante where nome like %:nome% and cozinha.id = :id" )
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinhaId);


}
