package com.example.springpfe.repository;

import com.example.springpfe.model.Commande;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommandeRepository  extends MongoRepository<Commande, String> {
}
