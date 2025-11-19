package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.MedidasDTO;
import com.proyectoT.sena.service.MedidasService;

@RestController
@RequestMapping("/api/medidas")
public class MedidasController {

    private final MedidasService service;

    public MedidasController(MedidasService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MedidasDTO> create(@RequestBody MedidasDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping
    public ResponseEntity<MedidasDTO> update(@RequestBody MedidasDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedidasDTO> getById(@PathVariable Long id) {
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MedidasDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

