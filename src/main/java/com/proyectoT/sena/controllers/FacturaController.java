package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.FacturaDTO;
import com.proyectoT.sena.service.FacturaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FacturaController {

    private final FacturaService facturaService;

    // ---------------------------------------------------------
    // CREATE
    // ---------------------------------------------------------
    @PostMapping
    public ResponseEntity<FacturaDTO> create(@RequestBody FacturaDTO dto) {
        return ResponseEntity.ok(facturaService.create(dto));
    }

    // ---------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> update(
            @PathVariable Long id,
            @RequestBody FacturaDTO dto) {

        dto.setId(id); // asegurar que el ID de ruta se use
        return ResponseEntity.ok(facturaService.update(dto));
    }

    // ---------------------------------------------------------
    // FIND ALL
    // ---------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<FacturaDTO>> findAll() {
        return ResponseEntity.ok(facturaService.findAll());
    }

    // ---------------------------------------------------------
    // FIND BY ID
    // ---------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.findById(id));
    }

    // ---------------------------------------------------------
    // DELETE
    // ---------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facturaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
