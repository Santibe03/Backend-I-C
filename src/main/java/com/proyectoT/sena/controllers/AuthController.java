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
    private final com.proyectoT.sena.service.UserRestauranteService userRestauranteService;

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

        // Mapeo robusto de roles
        String mappedRole = "client";
        java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> authorities = userDetails
                .getAuthorities();

        boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().toUpperCase().contains("ADMIN"));
        boolean isEmployee = authorities.stream().anyMatch(a -> a.getAuthority().toUpperCase().contains("EMPLOYEE"));

        if (isAdmin) {
            mappedRole = "admin";
        } else if (isEmployee) {
            mappedRole = "employee";
        }

        // Obtener restaurantes del usuario
        java.util.List<com.proyectoT.sena.dtos.UserRestauranteDTO> restaurantes = userRestauranteService
                .obtenerRestaurantesPorUsuario(user.getId());

        return ResponseEntity.ok(new AuthResponse(token, personId, user.getId(), mappedRole, restaurantes));
    }

    // REGISTRO
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequestDTO requestDTO) {
        UserDTO registeredUser = userService.registerUser(requestDTO);
        return ResponseEntity.ok(registeredUser);
    }

    // RECUPERACIÓN DE CONTRASEÑA - Paso 1: Solicitar reset
    @PostMapping("/request-password-reset")
    public ResponseEntity<?> requestPasswordReset(@RequestBody java.util.Map<String, String> request) {
        String email = request.get("email");

        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body(java.util.Map.of("message", "Email es requerido"));
        }

        java.util.Optional<com.proyectoT.sena.models.User> user = userService.requestPasswordReset(email);

        if (user.isPresent()) {
            // En producción, aquí se enviaría un email
            // Por ahora, mostramos el token en los logs
            System.out.println("==============================================");
            System.out.println("TOKEN DE RECUPERACIÓN GENERADO:");
            System.out.println("Email: " + email);
            System.out.println("Token: " + user.get().getResetKey());
            System.out.println("==============================================");
        }

        // Siempre retornamos el mismo mensaje por seguridad (no revelar si el email
        // existe)
        return ResponseEntity.ok(java.util.Map.of(
                "message",
                "Si el correo existe en nuestro sistema, recibirás instrucciones para restablecer tu contraseña"));
    }

    // RECUPERACIÓN DE CONTRASEÑA - Paso 2: Restablecer con token
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody com.proyectoT.sena.dtos.PasswordResetDTO request) {
        try {
            com.proyectoT.sena.models.User user = userService.completePasswordReset(
                    request.getNewPassword(),
                    request.getKey());

            System.out.println("Contraseña actualizada exitosamente para: " + user.getEmail());

            return ResponseEntity.ok(java.util.Map.of(
                    "message",
                    "Contraseña actualizada exitosamente. Ya puedes iniciar sesión con tu nueva contraseña."));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(java.util.Map.of(
                    "message", "Token inválido o expirado. Por favor, solicita un nuevo enlace de recuperación."));
        }
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
        private Long userId; // <--- AGREGADO
        private String role;
        private java.util.List<com.proyectoT.sena.dtos.UserRestauranteDTO> restaurantes;
    }

    @lombok.Data
    public static class ResetRequest {
        private String token;
        private String newPassword;
    }
}
