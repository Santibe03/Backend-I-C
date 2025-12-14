package com.proyectoT.sena.dtos;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservacionDTO {

    private Long id;

    private LocalDate aplicationDate;
    private ZonedDateTime reservationDate;
    private Integer attendat;

    // Relaciones simplificadas
    private Long personId; // ID of the person
    private com.proyectoT.sena.models.enums.ReservationCondition condition;
    private Long barTableId; // ID of the BarTable

}
