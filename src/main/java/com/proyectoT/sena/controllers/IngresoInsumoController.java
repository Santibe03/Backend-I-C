package com.proyectoT.sena.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.IngresoInsumoDTO;
import com.proyectoT.sena.service.IngresoInsumoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/income-inputs")
@RequiredArgsConstructor
public class IngresoInsumoController {

    private final IngresoInsumoService service;

    @PostMapping
    public ResponseEntity<IngresoInsumoDTO> create(@RequestBody IngresoInsumoDTO dto) {
        IngresoInsumoDTO result = service.save(dto);
        return ResponseEntity.created(URI.create("/api/income-inputs/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngresoInsumoDTO> update(
            @PathVariable Long id,
            @RequestBody IngresoInsumoDTO dto
    ) {
        dto.setId(id);
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping
    public List<IngresoInsumoDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngresoInsumoDTO> getOne(@PathVariable Long id) {
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
