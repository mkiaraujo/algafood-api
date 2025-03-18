package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers( "/v1/cozinhas/**").permitAll()
                                .anyRequest()
                                .authenticated() // Exige autenticação para /api/**
                )
                .httpBasic(httpBasic -> {}) // Habilita autenticação HTTP Basic
                .csrf(csrf -> csrf.disable()); // Desabilita CSRF para APIs RESTful


        return http.build();

    }
}
