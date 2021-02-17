package com.home.fitness.controllers;

import com.home.fitness.documents.User;
import com.home.fitness.payload.AuthRequest;
import com.home.fitness.payload.AuthResponse;
import com.home.fitness.repository.UsersRepository;
import com.home.fitness.security.AuthenticationManager;
import com.home.fitness.security.JwtUtil.JwtUtil;
import com.home.fitness.services.CustomUserDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class AuthController {
    private final Logger log = Logger.getLogger(AuthController.class);
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


//    @PostMapping("/login")
//    @CrossOrigin
//    public Mono<AuthResponse>login(AuthRequest request)  {
//
//                }
    }



