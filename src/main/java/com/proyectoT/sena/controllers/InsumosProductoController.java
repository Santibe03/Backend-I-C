package com.proyectoT.sena.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.InsumosProductoDTO;
import com.proyectoT.sena.service.InsumosProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/insumos-producto")
@RequiredArgsConstructor
public class InsumosProductoController {

    private final InsumosProductoService service;

    @PostMapping
    public ResponseEntity<InsumosProductoDTO> create(@RequestBody InsumosProductoDTO dto) {
        InsumosProductoDTO result = service.save(dto);
        return ResponseEntity.created(URI.create("/api/insumos-producto/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsumosProductoDTO> update(
            @PathVariable Long id,
            @RequestBody InsumosProductoDTO dto
    ) {
        dto.setId(id);
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping
    public List<InsumosProductoDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsumosProductoDTO> getOne(@PathVariable Long id) {
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
