package com.home.fitness.repository;

import com.home.fitness.documents.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UsersRepository extends ReactiveMongoRepository<Users, String> {
    Mono<UserDetails> findUsersByEmail(String email);

}
