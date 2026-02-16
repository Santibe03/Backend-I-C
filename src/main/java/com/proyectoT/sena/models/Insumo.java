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

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String inputName;

    @Size(max = 20)
    @Column(name = "brand", length = 20)
    private String brand;

    @Column(name = "current_stock")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    @NotNull
    private com.proyectoT.sena.models.enums.CategoriaEnum category;

    @Enumerated(EnumType.STRING)
    @Column(name = "measure")
    private Medida measure;

    @OneToMany(mappedBy = "input", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<IngresoInsumo> incomeInputs = new HashSet<>();

    @OneToMany(mappedBy = "input", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InsumosProducto> productInputs = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
}
