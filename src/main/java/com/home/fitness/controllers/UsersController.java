package com.home.fitness.controllers;


import com.home.fitness.document.Users;
import com.home.fitness.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;
    private static final Logger log = Logger.getLogger(UsersController.class);

    @GetMapping("/tweets")
    public Flux<Users> getAllTweets() {
        return usersRepository.findAll();
    }

    @PostMapping("/tweets")
    public Mono<Users> createTweets(@Valid @RequestBody Users users) {
        return usersRepository.save(users);
    }

    @GetMapping("/tweets/{id}")
    public Mono<ResponseEntity<Users>> getTweetById(@PathVariable(value = "id") String tweetId) {
        return usersRepository.findById(tweetId)
                .map(savedUsers -> ResponseEntity.ok(savedUsers))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/tweets/{id}")
    public Mono<ResponseEntity<Users>> updateTweet(@PathVariable(value = "id") String tweetId,
                                                   @Valid @RequestBody Users users) {
        return usersRepository.findById(tweetId)
                .flatMap(existingUsers -> {
                    existingUsers.setText(users.getText());
                    return usersRepository.save(existingUsers);
                })
                .map(updatedUsers -> new ResponseEntity<>(updatedUsers, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

