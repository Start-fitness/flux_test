package com.home.fitness.repository;

import com.home.fitness.documents.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends ReactiveMongoRepository<Users, String> {
}
