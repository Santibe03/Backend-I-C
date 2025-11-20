package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.OrdenDTO;
import com.proyectoT.sena.service.OrdenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public ResponseEntity<OrdenDTO> create(@RequestBody OrdenDTO dto) {
        return ResponseEntity.status(201).body(ordenService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenDTO> update(@PathVariable Long id, @RequestBody OrdenDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(ordenService.update(dto));
    }

    @GetMapping
    public ResponseEntity<List<OrdenDTO>> findAll() {
        return ResponseEntity.ok(ordenService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ordenService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
