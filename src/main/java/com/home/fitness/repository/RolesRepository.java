package com.home.fitness.repository;

import com.home.fitness.documents.Roles;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface RolesRepository extends ReactiveMongoRepository<Roles, String> {
    Roles findByRole(String role);
}
