package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoT.sena.dtos.SolicitudAsociacionDTO;
import com.proyectoT.sena.service.SolicitudAsociacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SolicitudAsociacionController {

    private final SolicitudAsociacionService solicitudService;

    @PostMapping
    public ResponseEntity<SolicitudAsociacionDTO> enviar(@RequestBody SolicitudRequest request) {
        SolicitudAsociacionDTO solicitud = solicitudService.enviarSolicitud(
                request.getUsuarioId(),
                request.getRestauranteId(),
                request.getEsParaAdministrador());
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping("/restaurante/{restauranteId}/pendientes")
    public ResponseEntity<List<SolicitudAsociacionDTO>> listarPendientes(@PathVariable Long restauranteId) {
        List<SolicitudAsociacionDTO> solicitudes = solicitudService.listarPendientesPorRestaurante(restauranteId);
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<SolicitudAsociacionDTO>> listarPorRestaurante(@PathVariable Long restauranteId) {
        List<SolicitudAsociacionDTO> solicitudes = solicitudService.listarTodasPorRestaurante(restauranteId);
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<SolicitudAsociacionDTO>> listarPorUsuario(@PathVariable Long userId) {
        List<SolicitudAsociacionDTO> solicitudes = solicitudService.listarPorUsuario(userId);
        return ResponseEntity.ok(solicitudes);
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<SolicitudAsociacionDTO> aprobar(
            @PathVariable Long id,
            @RequestParam Long usuarioAprobadorId) {
        SolicitudAsociacionDTO solicitud = solicitudService.aprobar(id, usuarioAprobadorId);
        return ResponseEntity.ok(solicitud);
    }

    @PutMapping("/{id}/rechazar")
    public ResponseEntity<SolicitudAsociacionDTO> rechazar(
            @PathVariable Long id,
            @RequestParam Long usuarioAprobadorId,
            @RequestParam(required = false) String motivoRechazo) {
        SolicitudAsociacionDTO solicitud = solicitudService.rechazar(id, usuarioAprobadorId, motivoRechazo);
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping("/usuario/{userId}/rechazadas/no-leidas")
    public ResponseEntity<List<SolicitudAsociacionDTO>> listarRechazadasNoLeidas(@PathVariable Long userId) {
        List<SolicitudAsociacionDTO> solicitudes = solicitudService.listarRechazadasNoLeidas(userId);
        return ResponseEntity.ok(solicitudes);
    }

    @PutMapping("/usuario/{userId}/marcar-leidas")
    public ResponseEntity<Void> marcarComoLeidas(@PathVariable Long userId) {
        solicitudService.marcarComoLeidas(userId);
        return ResponseEntity.ok().build();
    }

    // Clase interna para el request body
    @lombok.Data
    public static class SolicitudRequest {
        private Long usuarioId;
        private Long restauranteId;
        private Boolean esParaAdministrador;
    }
}
