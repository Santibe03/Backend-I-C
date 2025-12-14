package com.proyectoT.sena.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.ReservacionDTO;
import com.proyectoT.sena.service.ReservacionService;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionController {

    private final ReservacionService reservacionService;

    public ReservacionController(ReservacionService reservacionService) {
        this.reservacionService = reservacionService;
    }

    // ----------------------------------------------------
    // 📌 CREAR RESERVACIÓN
    // ----------------------------------------------------
    @PostMapping
    public ResponseEntity<ReservacionDTO> create(@RequestBody ReservacionDTO dto)
            throws URISyntaxException {

        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        ReservacionDTO result = reservacionService.save(dto);
        return ResponseEntity
                .created(new URI("/api/reservaciones/" + result.getId()))
                .body(result);
    }

    // ----------------------------------------------------
    // 📌 ACTUALIZAR RESERVACIÓN
    // ----------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<ReservacionDTO> update(
            @PathVariable Long id,
            @RequestBody ReservacionDTO dto) {

        if (dto.getId() == null) {
            dto.setId(id);
        }

        ReservacionDTO result = reservacionService.update(dto);
        return ResponseEntity.ok(result);
    }

    // ----------------------------------------------------
    // 📌 OBTENER UNA RESERVACIÓN POR ID
    // ----------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<ReservacionDTO> getOne(@PathVariable Long id) {

        Optional<ReservacionDTO> dto = reservacionService.findOne(id);

        return dto.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ----------------------------------------------------
    // 📌 LISTAR TODAS LAS RESERVACIONES
    // ----------------------------------------------------
    @GetMapping
    public ResponseEntity<List<ReservacionDTO>> getAll() {
        List<ReservacionDTO> list = reservacionService.findAll();
        return ResponseEntity.ok(list);
    }

    // ----------------------------------------------------
    // 📌 OBTENER RESERVACIONES POR PERSONA
    // ----------------------------------------------------
    @GetMapping("/person/{id}")
    public ResponseEntity<List<ReservacionDTO>> getByPersonId(@PathVariable Long id) {
        List<ReservacionDTO> list = reservacionService.findByPersonId(id);
        return ResponseEntity.ok(list);
    }

    // ----------------------------------------------------
    // 📌 ELIMINAR UNA RESERVACIÓN
    // ----------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
