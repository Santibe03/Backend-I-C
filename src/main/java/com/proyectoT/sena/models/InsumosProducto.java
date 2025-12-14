package com.proyectoT.sena.models;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "product_inputs")
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InsumosProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @lombok.EqualsAndHashCode.Include
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "input_id", nullable = false)
    private Insumo input;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "product_id", nullable = false)
    private Producto product;

    @Enumerated(EnumType.STRING)
    @Column(name = "measure")
    private Medida measure;
}