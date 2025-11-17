package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.service.BarraMesaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/barra-mesa")
@RequiredArgsConstructor
public class BarraMesaController {

    private final BarraMesaService service;

    @PostMapping
    public ResponseEntity<BarraMesaDTO> create(@RequestBody BarraMesaDTO dto) {
        BarraMesaDTO result = service.save(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarraMesaDTO> update(
            @PathVariable Long id,
            @RequestBody BarraMesaDTO dto) {

        dto.setId(id);
        BarraMesaDTO result = service.update(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<BarraMesaDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarraMesaDTO> getOne(@PathVariable Long id) {
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
