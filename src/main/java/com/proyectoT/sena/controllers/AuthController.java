package com.proyectoT.sena.controllers;

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
            new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        String token = jwtService.generarToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    // REGISTRO
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    // RECUPERAR: Paso 1 (Pedir token)
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        userService.requestPasswordReset(email).ifPresent(user -> {
            // AQUÍ IRÍA EL ENVÍO DE CORREO REAL.
            // Por ahora imprimimos en consola para que pruebes
            System.out.println("------------------------------------------------");
            System.out.println("TOKEN DE RECUPERACIÓN PARA " + email + ": " + user.getResetKey());
            System.out.println("------------------------------------------------");
        });
        return ResponseEntity.ok("Si el correo existe, se han enviado instrucciones.");
    }

    // RECUPERAR: Paso 2 (Usar token y nueva contraseña)
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetRequest resetRequest) {
        userService.completePasswordReset(resetRequest.getNewPassword(), resetRequest.getToken());
        return ResponseEntity.ok("Contraseña actualizada correctamente.");
    }

    // Clases DTO internas para recibir datos limpios
    @lombok.Data
    public static class LoginRequest {
        private String login;
        private String password;
    }

    @lombok.Data
    @lombok.AllArgsConstructor
    public static class AuthResponse {
        private String token;
    }

    @lombok.Data
    public static class ResetRequest {
        private String token;
        private String newPassword;
    }
}
