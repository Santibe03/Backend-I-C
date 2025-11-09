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
@Table(name = "bar_table") 
public class BarraMesa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bar_table") 
    private Long id;

    @NotNull
    @Column(name = "avalabily", nullable = false)
    private Boolean avalabily;

    @Column(name = "share")
    private Integer share;

    
    @OneToMany(mappedBy = "barTable", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Orden> orders = new HashSet<>(); 

    @OneToMany(mappedBy = "barTable", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Reservacion> reservations = new HashSet<>(); 
}