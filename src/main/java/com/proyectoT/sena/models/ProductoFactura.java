package com.proyectoT.sena.models;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product_bill") 
public class ProductoFactura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_factura")
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
    @JoinColumn(name = "bill_id", nullable = false)
    private Factura bill; 
}
