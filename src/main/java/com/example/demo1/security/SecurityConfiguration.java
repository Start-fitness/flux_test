//package com.example.demo1.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableReactiveMethodSecurity
//@EnableWebFluxSecurity
//public class SecurityConfiguration {
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
//        return httpSecurity
//                .csrf().disable()
//                .formLogin().and()
//                .httpBasic().disable()
//                .authorizeExchange()
////                .pathMatchers("/", "/login", "/favicon.ico")
////                .permitAll()
//                .anyExchange().authenticated()
//                .and()
//                .build();
//    }
//}
