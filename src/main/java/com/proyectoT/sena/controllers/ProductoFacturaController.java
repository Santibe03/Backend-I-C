package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.ProductoFacturaDTO;
import com.proyectoT.sena.service.ProductoFacturaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/producto-factura")
@RequiredArgsConstructor
public class ProductoFacturaController {

    private final ProductoFacturaService service;

    @PostMapping
    public ResponseEntity<ProductoFacturaDTO> create(@RequestBody ProductoFacturaDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoFacturaDTO> update(
            @PathVariable Long id,
            @RequestBody ProductoFacturaDTO dto
    ) {
        dto.setId(id);
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoFacturaDTO> findOne(@PathVariable Long id) {
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductoFacturaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
