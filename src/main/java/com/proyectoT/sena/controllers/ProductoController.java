package com.proyectoT.sena.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.proyectoT.sena.dtos.ProductoDTO;
import com.proyectoT.sena.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductoDTO> create(
            @RequestPart("name") String name,
            @RequestPart("price") Integer price,
            @RequestPart("productImage") MultipartFile productImage) {

        ProductoDTO dto = new ProductoDTO();
        dto.setName(name);
        dto.setPrice(price);
        dto.setProductImage(productImage);

        return ResponseEntity.ok(productoService.save(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductoDTO> update(
            @PathVariable Long id,
            @RequestPart("name") String name,
            @RequestPart("price") Integer price,
            @RequestPart("productImage") MultipartFile productImage) {

        ProductoDTO dto = new ProductoDTO();
        dto.setName(name);
        dto.setPrice(price);
        dto.setProductImage(productImage);

        return ResponseEntity.ok(productoService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.ok("Producto eliminado");
    }
}
