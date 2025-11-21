package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsumosProductoDTO {

    private Long id;
    private Integer amount;

    private Long inputId;
    private String inputNombre;   // Insumo.nombre

    private Long productId;
    private String productName;   // Producto.name

    private Long measureId;
    private String measureNombre; // Medidas.medNam
}

