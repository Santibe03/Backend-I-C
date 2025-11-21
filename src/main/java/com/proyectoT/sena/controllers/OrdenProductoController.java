package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.OrdenProductoDTO;
import com.proyectoT.sena.service.OrdenProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orden-producto")
@RequiredArgsConstructor
public class OrdenProductoController {

    private final OrdenProductoService service;

    @PostMapping
    public ResponseEntity<OrdenProductoDTO> create(@RequestBody OrdenProductoDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenProductoDTO> update(
            @PathVariable Long id,
            @RequestBody OrdenProductoDTO dto
    ) {
        dto.setId(id);
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenProductoDTO> findOne(@PathVariable Long id) {
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OrdenProductoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
