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
@Table(name = "order_product") 
public class OrdenProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_producto")
    private Long id;

   
    @Column(name = "amount")
    private Integer amount; 

    
    @Column(name = "price")
    private Integer price;

    
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "product_id", nullable = false)
    private Producto product; 

    
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "order_id", nullable = false)
    private Orden order; 
}
