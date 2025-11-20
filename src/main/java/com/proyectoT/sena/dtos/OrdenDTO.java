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

    // relaciones por id
    private Long barraMesaId;
    private Long condicionId;

    // campos opcionales para vistas
    private String barraMesaNombre;
    private String condicionNombre;
    private Integer cantidadProductos;
}
