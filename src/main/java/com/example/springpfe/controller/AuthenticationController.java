package com.example.springpfe.controller;


import com.example.springpfe.config.CustomLogoutHandler;
import com.example.springpfe.model.AuthenticationResponse;
import com.example.springpfe.model.User;
import com.example.springpfe.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;
    private final CustomLogoutHandler customLogoutHandler; // inject custom logout handler

    public AuthenticationController(AuthenticationService authService, CustomLogoutHandler customLogoutHandler) {
        this.authService = authService;
        this.customLogoutHandler = customLogoutHandler;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return authService.refreshToken(request, response);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        customLogoutHandler.logout(request, response, null); // call the logout handler

        return ResponseEntity.ok().build(); // return a success response
    }
}