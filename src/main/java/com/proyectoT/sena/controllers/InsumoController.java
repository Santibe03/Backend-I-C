package com.proyectoT.sena.controllers;

import com.proyectoT.sena.dtos.InsumoDTO;
import com.proyectoT.sena.service.InsumoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/insumos")
@CrossOrigin(origins = "*")
public class InsumoController {

    private final InsumoService insumoService;

    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
    }

    // Crear
    @PostMapping
    public ResponseEntity<InsumoDTO> create(@RequestBody InsumoDTO dto) {
        InsumoDTO saved = insumoService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<InsumoDTO> update(@PathVariable Long id, @RequestBody InsumoDTO dto) {
        dto.setId(id);
        InsumoDTO updated = insumoService.update(dto);
        return ResponseEntity.ok(updated);
    }

    // Obtener uno
    @GetMapping("/{id}")
    public ResponseEntity<InsumoDTO> findOne(@PathVariable Long id) {
        Optional<InsumoDTO> dto = insumoService.findOne(id);
        return dto.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // Obtener todos
    @GetMapping
    public ResponseEntity<List<InsumoDTO>> findAll() {
        return ResponseEntity.ok(insumoService.findAll());
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        insumoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
