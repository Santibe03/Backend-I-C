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
    private PersonDTO person;
    private CondicionDTO condicion;
    private BarraMesaDTO barraMesa;

}

