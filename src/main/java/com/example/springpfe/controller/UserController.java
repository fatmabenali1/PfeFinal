package com.example.springpfe.controller;

import com.example.springpfe.model.User;

import com.example.springpfe.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService utilisateurService;

    // 🔹 Récupérer tous les utilisateurs (accessible uniquement aux admins)
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return utilisateurService.getAllUtilisateurs();
    }

    // 🔹 Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return utilisateurService.getUtilisateurById(id);
    }

    // 🔹 Ajouter un utilisateur

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user, Authentication authentication) {
        System.out.println("Authenticated User: " + authentication.getName());
        System.out.println("Roles: " + authentication.getAuthorities());

        // Vérifie si l'utilisateur a bien le rôle ADMIN
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            // Logique pour créer un utilisateur
            User createdUser = utilisateurService.createUtilisateur(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
    }


    // 🔹 Modifier un utilisateur
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User utilisateur) {
        return utilisateurService.updateUtilisateur(id, utilisateur);
    }

    // 🔹 Supprimer un utilisateur
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        return utilisateurService.deleteUtilisateur(id);
    }
}
