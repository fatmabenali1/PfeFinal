package com.example.springpfe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {

    @Id
    private String id;

    private String accessToken;
    private String refreshToken;
    private boolean loggedOut;

    @DBRef
    private User user;
}