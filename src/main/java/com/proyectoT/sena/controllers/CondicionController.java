package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.CondicionDTO;
import com.proyectoT.sena.service.CondicionService;

@RestController
@RequestMapping("/api/condicion")
public class CondicionController {

    private final CondicionService service;

    public CondicionController(CondicionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CondicionDTO> create(@RequestBody CondicionDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping
    public ResponseEntity<CondicionDTO> update(@RequestBody CondicionDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondicionDTO> getOne(@PathVariable Long id) {
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CondicionDTO> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
