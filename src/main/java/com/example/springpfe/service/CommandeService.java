package com.example.springpfe.service;

import com.example.springpfe.model.Commande;
import com.example.springpfe.model.User;
import com.example.springpfe.repository.CommandeRepository;
import com.example.springpfe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final UserRepository utilisateurRepository; // Assure-toi d'avoir ce repository

    public Commande ajouterCommande(Commande commande, String idUser) {
        Optional<User> utilisateur = utilisateurRepository.findById(idUser);

        if (utilisateur.isPresent()) {
            commande.setDate(LocalDate.now()); // Définit la date actuelle
            return commandeRepository.save(commande);
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + idUser);
        }
    }

    // Récupérer toutes les commandes
    public List<Commande> obtenirToutesLesCommandes() {
        return commandeRepository.findAll();
    }

    // Récupérer une commande par ID
    public Optional<Commande> obtenirCommandeParId(String id) {
        return commandeRepository.findById(id);
    }

    // Mettre à jour une commande
    public Commande mettreAJourCommande(String id, Commande nouvelleCommande) {
        return commandeRepository.findById(id).map(commande -> {
            commande.setAdresseLiv(nouvelleCommande.getAdresseLiv());
            commande.setStatus(nouvelleCommande.getStatus());
            commande.setDate(nouvelleCommande.getDate());
            return commandeRepository.save(commande);
        }).orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }

    // Supprimer une commande
    public void supprimerCommande(String id) {
        commandeRepository.deleteById(id);
    }

}
