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

    private Long productId;
    private Long billId;

    // Información opcional
    private String productName;
}

