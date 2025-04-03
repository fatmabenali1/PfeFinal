package com.example.springpfe.repository;
import com.example.springpfe.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    // 🔹 Trouver un utilisateur par son email (optionnel si besoin)
    Optional<User> findByEmail(String email);

}