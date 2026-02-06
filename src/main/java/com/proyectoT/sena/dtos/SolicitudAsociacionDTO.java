package com.proyectoT.sena.dtos;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudAsociacionDTO {

    private Long id;
    private Long usuarioSolicitanteId;
    private String nombreSolicitante; // Para mostrar en UI
    private Long restauranteId;
    private String nombreRestaurante;
    private Boolean esParaAdministrador;
    private String estado; // PENDIENTE, APROBADA, RECHAZADA
    private Instant fechaSolicitud;
    private Instant fechaRespuesta;
    private Long usuarioAprobadorId;
}
