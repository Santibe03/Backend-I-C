package com.proyectoT.sena.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "shtp_order") 
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "bar_table_id", nullable = false)
    private BarraMesa barTable; 

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "condition_id", nullable = false)
    private Condicion condition; 

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonIgnore // Para evitar ciclos de serialización JSON
    private Set<OrdenProducto> orderProducts = new HashSet<>(); 
}
