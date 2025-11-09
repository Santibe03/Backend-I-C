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
@Table(name = "measure")
public class Medidas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medida")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "measure_name", length = 20, nullable = false, unique = true) 
    private String medNam;

    @NotNull
    @Size(max = 5)
    @Column(name = "abreviation", length = 5, nullable = false, unique = true) 
    private String abreviacion;

    
    @OneToMany(mappedBy = "measure", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<IngresoInsumo> incomeInputs = new HashSet<>();

    
    @OneToMany(mappedBy = "measure", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InsumosProducto> productInputs = new HashSet<>();

    
    @OneToMany(mappedBy = "measure", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Insumo> inputs = new HashSet<>();
}
