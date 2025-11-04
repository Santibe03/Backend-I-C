package com.proyectoT.sena.models;

import java.io.Serializable;
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
@Table(name = "authority")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Id
    @Column(name = "name", length = 50, nullable = false)
    private String name; // Ejemplo: ROLE_ADMIN, ROLE_USER

    // --- Relación con tabla intermedia ---
    @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserAuthority> userAuthorities = new HashSet<>();
}
