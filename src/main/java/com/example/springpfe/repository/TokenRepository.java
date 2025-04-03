package com.example.springpfe.repository;
import com.example.springpfe.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {

    Optional<Token> findByAccessToken(String accessToken);
    Optional<Token> findByRefreshToken(String refreshToken);
    List<Token> findAllByUserIdAndLoggedOutFalse(String userId);
}