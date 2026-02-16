package com.proyectoT.sena.service;

import java.util.List;

import com.proyectoT.sena.dtos.SolicitudAsociacionDTO;

public interface SolicitudAsociacionService {

    SolicitudAsociacionDTO enviarSolicitud(Long usuarioId, Long restauranteId, Boolean esParaAdministrador);

    SolicitudAsociacionDTO aprobar(Long solicitudId, Long usuarioAprobadorId);

    SolicitudAsociacionDTO rechazar(Long solicitudId, Long usuarioAprobadorId, String motivoRechazo);

    List<SolicitudAsociacionDTO> listarPendientesPorRestaurante(Long restauranteId);

    List<SolicitudAsociacionDTO> listarPorUsuario(Long usuarioId);

    List<SolicitudAsociacionDTO> listarTodasPorRestaurante(Long restauranteId);

    List<SolicitudAsociacionDTO> listarRechazadasNoLeidas(Long usuarioId);

    void marcarComoLeidas(Long usuarioId);
}
