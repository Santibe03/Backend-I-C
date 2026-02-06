package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.RestauranteDTO;
import com.proyectoT.sena.service.RestauranteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/restaurantes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RestauranteController {

    private final RestauranteService restauranteService;

    @PostMapping
    public ResponseEntity<RestauranteDTO> crear(
            @RequestBody RestauranteDTO dto,
            @RequestParam Long usuarioCreadorId) {
        RestauranteDTO created = restauranteService.crear(dto, usuarioCreadorId);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> listar() {
        List<RestauranteDTO> restaurantes = restauranteService.listar();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestauranteDTO>> buscar(@RequestParam String q) {
        List<RestauranteDTO> restaurantes = restauranteService.buscarPorNombre(q);
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> obtenerPorId(@PathVariable Long id) {
        RestauranteDTO restaurante = restauranteService.obtenerPorId(id);
        return ResponseEntity.ok(restaurante);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<RestauranteDTO>> listarPorUsuario(@PathVariable Long userId) {
        List<RestauranteDTO> restaurantes = restauranteService.listarPorUsuario(userId);
        return ResponseEntity.ok(restaurantes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> actualizar(
            @PathVariable Long id,
            @RequestBody RestauranteDTO dto) {
        RestauranteDTO updated = restauranteService.actualizar(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        restauranteService.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}
