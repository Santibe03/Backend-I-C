package com.proyectoT.sena.models;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "solicitud_asociacion")
public class SolicitudAsociacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "usuario_solicitante_id", nullable = false)
    private User usuarioSolicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @Column(name = "es_para_administrador", nullable = false)
    private Boolean esParaAdministrador = false;

    @NotNull
    @Size(max = 20)
    @Column(name = "estado", length = 20, nullable = false)
    private String estado = "PENDIENTE"; // PENDIENTE, APROBADA, RECHAZADA

    @Column(name = "fecha_solicitud")
    private Instant fechaSolicitud = Instant.now();

    @Column(name = "fecha_respuesta")
    private Instant fechaRespuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_aprobador_id")
    private User usuarioAprobador;
}
