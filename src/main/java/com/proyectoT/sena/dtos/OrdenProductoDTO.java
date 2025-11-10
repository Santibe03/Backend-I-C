package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenProductoDTO {

    private Long id;
    private Integer amount;
    private Integer price;

    // Relaciones representadas por ID
    private Long productId;
    private Long orderId;

    // Campos opcionales para mostrar información descriptiva
    private String productName;
    private String orderCode; // opcional, si tu orden tiene un código o número visible
}