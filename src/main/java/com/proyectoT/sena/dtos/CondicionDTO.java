package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CondicionDTO {

    private Long id;
    private String conditionName;

    // Campos opcionales de resumen (si luego quieres agregar datos derivados)
    private Integer totalOrders;
    private Integer totalReservations;
}
