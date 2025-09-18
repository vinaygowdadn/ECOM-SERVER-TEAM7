package com.bootA.ecom_server.repos;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bootA.ecom_server.models.user;

public interface UserRepo extends MongoRepository<user, String> {
    Optional<user> findByEmail(String email);
}
