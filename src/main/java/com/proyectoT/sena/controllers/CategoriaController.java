package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.CategoriaDTO;
import com.proyectoT.sena.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    // CREATE
    @PostMapping
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO dto) {
        CategoriaDTO result = categoriaService.save(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(
            @PathVariable Long id,
            @RequestBody CategoriaDTO dto) {

        dto.setId(id); // aseguramos que el ID del path se use
        CategoriaDTO result = categoriaService.update(dto);
        return ResponseEntity.ok(result);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAll() {
        List<CategoriaDTO> list = categoriaService.findAll();
        return ResponseEntity.ok(list);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getOne(@PathVariable Long id) {
        return categoriaService.findOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
