package com.example.springpfe.service;
import com.example.springpfe.model.User;
import com.example.springpfe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/users")
public class UserService {
    @Autowired
    private UserRepository utilisateurRepository;

    public List<User> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // 🔹 Récupérer un utilisateur par ID
    public ResponseEntity<User> getUtilisateurById(String id) {
        Optional<User> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Ajouter un utilisateur
    public User createUtilisateur(User user) {
        // Vérifie si un utilisateur avec le même nom d'utilisateur existe déjà
        if (utilisateurRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        return utilisateurRepository.save(user);

    }
    // 🔹 Modifier un utilisateur
    public ResponseEntity<User> updateUtilisateur(String id, User utilisateurDetails) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateur.setFirstName(utilisateurDetails.getFirstName());
            utilisateur.setLastName(utilisateurDetails.getLastName());
            utilisateur.setEmail(utilisateurDetails.getEmail());
            utilisateur.setRole(utilisateurDetails.getRole());
            User updatedUser = utilisateurRepository.save(utilisateur);
            return ResponseEntity.ok(updatedUser);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Supprimer un utilisateur
    public ResponseEntity<?> deleteUtilisateur(String id) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateurRepository.delete(utilisateur);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
