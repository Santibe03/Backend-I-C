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
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "product")
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    @lombok.EqualsAndHashCode.Include
    private Long id;

    @Size(max = 55)
    @Column(name = "name", length = 55)
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image_url")
    private String imageUrl;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "stock")
    private Integer stock;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<InsumosProducto> productInputs = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OrdenProducto> orderProducts = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ProductoFactura> productBills = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
}
