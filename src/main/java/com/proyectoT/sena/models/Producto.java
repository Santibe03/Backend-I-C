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
@Table(name = "product")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Size(max = 55)
    @Column(name = "name", length = 55)
    private String name; 

    @Column(name = "price")
    private Integer price;

    @Lob // Para manejar grandes objetos binarios (imágenes/archivos)
    @NotNull
    @Column(name = "product_image", nullable = false)
    private byte[] productImage;

    @NotNull
    @Column(name = "product_image_content_type", nullable = false)
    private String productImageContentType;

   
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InsumosProducto> productInputs = new HashSet<>();

   
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OrdenProducto> orderProducts = new HashSet<>();

   
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ProductoFactura> productBills = new HashSet<>();
}
