package com.home.fitness.controllers;


import com.home.fitness.documents.Users;
import com.home.fitness.repository.UsersRepository;
import com.home.fitness.services.CustomUserDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsersController {
    @Autowired
    private CustomUserDetailsService userService;
    @Autowired
    private UsersRepository usersRepository;
    private static final Logger log = Logger.getLogger(UsersController.class);

    @GetMapping("/users")
    public Flux<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @PostMapping("/register")
    @CrossOrigin(origins = {"http://localhost:4200"})
    public Mono<Users> createUsers(@Valid @RequestBody Users user) {
        return userService.saveUser(user);
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

