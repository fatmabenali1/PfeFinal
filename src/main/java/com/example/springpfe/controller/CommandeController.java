package com.example.springpfe.controller;

import com.example.springpfe.model.Commande;
import com.example.springpfe.service.CommandeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {
    private final CommandeService commandeService;

    // Ajouter une nouvelle commande
    @PostMapping("/add/{idUser}")
    public Commande ajouterCommande(@Valid @RequestBody Commande commande, @PathVariable("idUser") String idUser) {
        return commandeService.ajouterCommande(commande, idUser);
    }

    // Récupérer toutes les commandes
    @GetMapping
    public ResponseEntity<List<Commande>> obtenirToutesLesCommandes() {
        return ResponseEntity.ok(commandeService.obtenirToutesLesCommandes());
    }

    // Récupérer une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande> obtenirCommandeParId(@PathVariable String id) {
        Optional<Commande> commande = commandeService.obtenirCommandeParId(id);
        return commande.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour une commande
    @PutMapping("/{id}")
    public ResponseEntity<Commande> mettreAJourCommande(@PathVariable String id, @RequestBody Commande nouvelleCommande) {
        try {
            return ResponseEntity.ok(commandeService.mettreAJourCommande(id, nouvelleCommande));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCommande(@PathVariable String id) {
        commandeService.supprimerCommande(id);
        return ResponseEntity.noContent().build();
    }
}
