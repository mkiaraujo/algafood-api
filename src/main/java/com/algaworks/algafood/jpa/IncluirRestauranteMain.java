package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class IncluirRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        Restaurante portugues = new Restaurante();
        portugues.setNome("portugues");
        portugues.setTaxaFrete(new BigDecimal(10.10));

        Restaurante holandes = new Restaurante();
        holandes.setNome("Holandes");
        holandes.setTaxaFrete(new BigDecimal(5.25));

        portugues = restauranteRepository.salvar(portugues);
        holandes = restauranteRepository.salvar(holandes);

        System.out.println(portugues);
        System.out.println(holandes);

    }
}
