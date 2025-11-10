package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoFacturaDTO {

    private Long id;
    private Integer amount;
    private Integer price;

    // IDs de las relaciones
    private Long productId;
    private Long billId;

    // Campos opcionales descriptivos
    private String productName;
    private String billNumber;
}
