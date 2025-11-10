package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long id;
    private String name;
    private Integer price;

    // Imagen codificada en Base64 para enviar al frontend
    private String productImageBase64;
    private String productImageContentType;

    // Campos de resumen opcionales
    private Integer totalInsumos;
    private Integer totalOrdenes;
    private Integer totalFacturas;
}
