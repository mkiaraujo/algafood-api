package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class AlteracaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        Restaurante alteraRestauranteTailandesParaIndiano = new Restaurante();
        alteraRestauranteTailandesParaIndiano.setId(1L);
        alteraRestauranteTailandesParaIndiano.setNome("Indiano");
        alteraRestauranteTailandesParaIndiano.setTaxaFrete(new BigDecimal(2.25));

        restauranteRepository.salvar(alteraRestauranteTailandesParaIndiano);
    }
}
