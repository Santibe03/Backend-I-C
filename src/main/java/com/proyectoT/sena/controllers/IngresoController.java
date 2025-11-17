package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.IngresoDTO;
import com.proyectoT.sena.service.IngresoService;

@RestController
@RequestMapping("/api/ingreso")
public class IngresoController {

    private final IngresoService ingresoService;

    public IngresoController(IngresoService ingresoService) {
        this.ingresoService = ingresoService;
    }

    @PostMapping
    public ResponseEntity<IngresoDTO> create(@RequestBody IngresoDTO dto) {
        return new ResponseEntity<>(ingresoService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<IngresoDTO> update(@RequestBody IngresoDTO dto) {
        return ResponseEntity.ok(ingresoService.update(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngresoDTO> getById(@PathVariable Long id) {
        return ingresoService.findOne(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<IngresoDTO>> getAll() {
        return ResponseEntity.ok(ingresoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingresoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
