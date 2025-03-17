package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll() // Permite acesso público
                        .requestMatchers("/v1/**").authenticated() // Exige autenticação para /api/**
                )
                .httpBasic(httpBasic -> {}) // Habilita autenticação HTTP Basic
                .csrf(csrf -> csrf.disable()); // Desabilita CSRF para APIs RESTful

        return http.build();

    }
}
