package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.proyectoT.sena.models.Medida;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsumosProductoDTO {

    private Long id;
    private Double amount;

    private Long inputId;
    private String inputNombre; // Insumo.nombre

    private Long productId;
    private String productName; // Producto.name

    private com.proyectoT.sena.models.Medida measure;
}
