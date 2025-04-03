package com.example.springpfe.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "commandes") // Définition de la collection MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande {
    @Id
    private String id;

    private String adresseLiv;

    private StatusCommande status;

    private LocalDate date;
}
