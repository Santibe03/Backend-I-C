package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarraMesaDTO {

    private Long id;
    private Boolean avalabily;
    private Integer share;

    // Campos opcionales de resumen
    private Integer totalOrders;
    private Integer totalReservations;
}
