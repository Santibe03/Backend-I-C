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
@Table(name = "income_inputs") 
public class IngresoInsumo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_IngInsu")
    private Long id;

    @Column(name = "amount")
    private Integer amount;

    
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "input_id", nullable = false)
    private Insumo input; 

    
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "measure_id", nullable = false)
    private Medidas measure; 

    
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "income_id", nullable = false)
    private Ingresos income; 
}
