package com.home.fitness.controllers;


import com.home.fitness.documents.Users;
import com.home.fitness.repository.UsersRepository;
import com.home.fitness.security.JwtUtil.JwtUtil;
import com.home.fitness.services.CustomUserDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsersController {
    private final static ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    private static final Logger log = Logger.getLogger(UsersController.class);
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CustomUserDetailsService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    @CrossOrigin(origins = {"http://localhost:4200"})
    public Mono<Users> createUsers(@Valid @RequestBody Users user) {
        return userService.saveUser(user);
    }


    @PostMapping("/login")
    @CrossOrigin(origins = {"http://localhost:4200"})
    public Mono<ResponseEntity<?>> login(ServerWebExchange swe) {
        return swe.getFormData()
                .flatMap(credentials -> userService.findByUsername(credentials.getFirst("email"))
                        .cast(Users.class)
                        .map(userDetails -> Objects.equals(credentials.getFirst("password"),
                                userDetails.getPassword())
                                ? ResponseEntity.ok(jwtUtil.generateToken(userDetails))
                                : UNAUTHORIZED
                        )
                        .defaultIfEmpty(UNAUTHORIZED)
                );
    }

    @GetMapping("/users")
    public Flux<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<Users>> getUserById(@PathVariable(value = "id") String userId) {
        return usersRepository.findById(userId)
                .map(savedUsers -> ResponseEntity.ok(savedUsers))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}")
    public Mono<ResponseEntity<Users>> updateUser(@PathVariable(value = "id") String userId,
                                                  @Valid @RequestBody Users users) {
        return usersRepository.findById(userId)
                .flatMap(existingUsers -> {
                    existingUsers.setEmail(users.getEmail());
                    return usersRepository.save(existingUsers);
                })
                .map(updatedUsers -> new ResponseEntity<>(updatedUsers, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

