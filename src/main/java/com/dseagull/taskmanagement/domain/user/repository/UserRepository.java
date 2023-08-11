package com.dseagull.taskmanagement.domain.user.repository;

import com.dseagull.taskmanagement.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
