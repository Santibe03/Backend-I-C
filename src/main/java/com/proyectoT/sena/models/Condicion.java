package com.proyectoT.sena.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.*; // Añadida la importación para @NotNull
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "condition") // Nombre de tabla estandarizado a 'condition'
public class Condicion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull 
    @Size(max = 20)
    @Column(name = "condition_name", length = 20, nullable = false, unique = true) 
    private String conditionName;

   
    @OneToMany(mappedBy = "condition", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Orden> orders = new HashSet<>(); 

    
    @OneToMany(mappedBy = "condition", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Reservacion> reservations = new HashSet<>(); 
}