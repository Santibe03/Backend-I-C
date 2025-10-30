package com.proyectoT.sena.models;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name; // Ejemplo de valor: "ROLE_ADMIN", "ROLE_USER"
}