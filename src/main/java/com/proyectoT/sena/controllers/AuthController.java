package com.proyectoT.sena.controllers;

import com.proyectoT.sena.dtos.RegisterRequestDTO;
import com.proyectoT.sena.dtos.UserDTO;
import com.proyectoT.sena.service.JwtService;
import com.proyectoT.sena.service.UserService;
import com.proyectoT.sena.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    // LOGIN: Devuelve el Token JWT
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        String token = jwtService.generarToken(userDetails);

        // Fetch user properly to get person ID
        com.proyectoT.sena.models.User user = userService.findByEmail(request.getLogin())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long personId = user.getPerson() != null ? user.getPerson().getId() : null;

        String role = userDetails.getAuthorities().stream().findFirst().map(a -> a.getAuthority())
                .orElse("ROLE_CLIENT");
        // Simple mapping
        String mappedRole = "client";
        if (role.contains("ADMIN"))
            mappedRole = "admin";
        else if (role.contains("EMPLOYEE"))
            mappedRole = "employee";

        return ResponseEntity.ok(new AuthResponse(token, personId, mappedRole));
    }

    // REGISTRO
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequestDTO requestDTO) {
        UserDTO registeredUser = userService.registerUser(requestDTO);
        return ResponseEntity.ok(registeredUser);
    }

    // Clases DTO internas
    @lombok.Data
    public static class LoginRequest {
        private String login;
        private String password;
    }

    @lombok.Data
    @lombok.AllArgsConstructor
    public static class AuthResponse {
        private String token;
        private Long personId;
        private String role;
    }

    @lombok.Data
    public static class ResetRequest {
        private String token;
        private String newPassword;
    }
}
