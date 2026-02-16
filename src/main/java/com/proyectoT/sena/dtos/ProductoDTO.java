package com.proyectoT.sena.dtos;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;
    private String description;

    // Stock calculado automáticamente basado en insumos disponibles y receta
    private Integer calculatedStock;

    private Long restauranteId;
}
