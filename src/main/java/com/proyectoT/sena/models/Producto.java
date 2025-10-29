package com.proyectoT.sena.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long id;

    @Column(length = 55)
    private String nom;

    @Column(nullable = false)
    private byte[] productImage;

    @Column(nullable = false)
    private String productImageContentType;
    
    private Integer price;
    
}
