package com.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {

        httpSecurity
                .authorizeExchange(exchange ->
                        exchange
                                .anyExchange()
                                .authenticated()
//                                .and()
//                                .oauth2Login()
//                                .and()
//                                .oauth2ResourceServer()
//                                .jwt()
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF protection

        return httpSecurity.build();
    }
}
