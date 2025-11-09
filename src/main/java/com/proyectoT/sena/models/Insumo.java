package com.proyectoT.sena.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.*; // Importación para @NotNull, @Size
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore; // Para evitar ciclos de JSON

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "input") 
public class Insumo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Long id;

    @NotNull
    @Size(max = 50) 
    @Column(name = "input_name", length = 50, nullable = false, unique = true)
    private String insuNam; 

    @Size(max = 20)
    @Column(name = "brand", length = 20)
    private String marca;

    
    @Column(name = "current_stock") 
    private Integer amount; 


    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "category_id", nullable = false) 
    private Categoria categoria;

   
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "measure_id", nullable = false)
    private Medidas measure; 


    @OneToMany(mappedBy = "input", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<IngresoInsumo> incomeInputs = new HashSet<>();

   
    @OneToMany(mappedBy = "input", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InsumosProducto> productInputs = new HashSet<>();
}

