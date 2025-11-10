package com.proyectoT.sena.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDTO {

    private Long id;
    private LocalDate date;

    // IDs de las relaciones
    private Long barTableId;
    private Long conditionId;

    // Campos opcionales descriptivos
    private String tableNumber;
    private String conditionName;
}