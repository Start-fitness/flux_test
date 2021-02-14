package com.example.demo1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;


@Configuration(proxyBeanMethods = false)
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                ).accessDeniedHandler((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                ).and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
//                .authenticationManager(authenticationManager)
//                .securityContextRepository(securityContextRepository)
//                .authorizeExchange()
//                .pathMatchers(HttpMethod.OPTIONS).permitAll()
//                .pathMatchers("/auth/login/guest").permitAll()
//                .anyExchange().authenticated()
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
