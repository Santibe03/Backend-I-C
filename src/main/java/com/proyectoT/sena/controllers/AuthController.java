package com.proyectoT.sena.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.repositoryes.UserRepository;
import com.proyectoT.sena.service.JwtService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    // -------- LOGIN --------
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getLogin(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generarToken(userDetails);

            return new AuthResponse(token);

        } catch (AuthenticationException e) {
            return new AuthResponse("ERROR: Credenciales incorrectas");
        }
    }

    // -------- REQUEST DTO --------
    public static class LoginRequest {
        private String login;
        private String password;

        public String getLogin() { return login; }
        public void setLogin(String login) { this.login = login; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // -------- RESPONSE DTO --------
    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) { this.token = token; }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }
}
