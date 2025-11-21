package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {

    private Long id;

    private Integer total;
    private LocalDate date;

    // Relación con Persona
    private Long personId;

    // Datos extra (opcionales) — por si quieres mostrarlos en el frontend
    private String personNombre;

    // Cantidad de productos asociados (opcional)
    private Integer cantidadProductos;
}
