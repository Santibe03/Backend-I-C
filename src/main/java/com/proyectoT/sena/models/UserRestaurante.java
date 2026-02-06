package com.proyectoT.sena.models;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_restaurante")
public class UserRestaurante implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserRestauranteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @lombok.ToString.Exclude
    @lombok.EqualsAndHashCode.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restauranteId")
    @JoinColumn(name = "restaurante_id")
    @lombok.ToString.Exclude
    @lombok.EqualsAndHashCode.Exclude
    private Restaurante restaurante;

    @Column(name = "es_administrador", nullable = false)
    private Boolean esAdministrador = false;

    @Column(name = "fecha_asociacion")
    private Instant fechaAsociacion = Instant.now();

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;
}
