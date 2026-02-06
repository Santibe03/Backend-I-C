package com.proyectoT.sena.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "restaurante")
public class Restaurante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    @NotNull
    @Size(max = 255)
    @Column(name = "direccion", length = 255, nullable = false)
    private String direccion;

    @NotNull
    @Size(max = 50)
    @Column(name = "contacto", length = 50, nullable = false)
    private String contacto;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion = Instant.now();

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    // Relación con el usuario que creó el restaurante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_creador_id")
    private User usuarioCreador;

    // Relación con usuarios asociados al restaurante
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @lombok.ToString.Exclude
    @lombok.EqualsAndHashCode.Exclude
    private Set<UserRestaurante> userRestaurantes = new HashSet<>();

    // Relación con reservaciones
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
    @JsonIgnore
    @lombok.ToString.Exclude
    @lombok.EqualsAndHashCode.Exclude
    private Set<Reservacion> reservaciones = new HashSet<>();
}
