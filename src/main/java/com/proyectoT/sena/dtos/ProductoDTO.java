package com.proyectoT.sena.dtos;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;
}

