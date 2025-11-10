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

    // En lugar de enviar todo el objeto, solo enviamos los IDs relacionados
    private Long personId;
    private Long conditionId;
    private Long barTableId;

    // Si lo deseas, puedes agregar nombres de referencia (opcional)
    private String personName;
    private String conditionName;
    private String tableNumber;
}

