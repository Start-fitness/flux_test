package com.home.fitness.controllers;


import com.home.fitness.documents.User;
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
    private static final Logger log = Logger.getLogger(UsersController.class);
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CustomUserDetailsService userService;

    @PostMapping("/register")
    @CrossOrigin(origins = {"http://localhost:4200"})
    public Mono<User> createUsers(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }




    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable(value = "id") String userId) {
        return usersRepository.findById(userId)
                .map(savedUsers -> ResponseEntity.ok(savedUsers))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable(value = "id") String userId,
                                                 @Valid @RequestBody User user) {
        return usersRepository.findById(userId)
                .flatMap(existingUsers -> {
                    existingUsers.setEmail(user.getEmail());
                    return usersRepository.save(existingUsers);
                })
                .map(updatedUsers -> new ResponseEntity<>(updatedUsers, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

