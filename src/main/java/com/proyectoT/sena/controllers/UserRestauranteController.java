package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.UserRestauranteDTO;
import com.proyectoT.sena.service.UserRestauranteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user-restaurantes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserRestauranteController {

    private final UserRestauranteService userRestauranteService;

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<UserRestauranteDTO>> obtenerPorUsuario(@PathVariable Long userId) {
        List<UserRestauranteDTO> restaurantes = userRestauranteService.obtenerRestaurantesPorUsuario(userId);
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<UserRestauranteDTO>> obtenerPorRestaurante(@PathVariable Long restauranteId) {
        List<UserRestauranteDTO> usuarios = userRestauranteService.obtenerUsuariosPorRestaurante(restauranteId);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{userId}/{restauranteId}/permisos")
    public ResponseEntity<UserRestauranteDTO> cambiarPermisos(
            @PathVariable Long userId,
            @PathVariable Long restauranteId,
            @RequestParam Boolean esAdministrador) {
        UserRestauranteDTO updated = userRestauranteService.cambiarPermisos(userId, restauranteId, esAdministrador);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{userId}/{restauranteId}")
    public ResponseEntity<Void> remover(
            @PathVariable Long userId,
            @PathVariable Long restauranteId) {
        userRestauranteService.removerUsuario(userId, restauranteId);
        return ResponseEntity.noContent().build();
    }
}
