package com.proyectoT.sena.models;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product_inputs") 
public class InsumosProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Long id;

    @Column(name = "amount")
    private Integer amount;

    
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "input_id", nullable = false)
    private Insumo input; 


    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "product_id", nullable = false)
    private Producto product; 


    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "measure_id", nullable = false)
    private Medidas measure; 
}