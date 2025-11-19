package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsumoDTO {

    private Long id;

    private String nombre;      // inputName
    private String marca;
    private Integer cantidad;   // current_stock

    // Relaciones representadas por ID
    private Long categoriaId;
    private Long medidaId;

    // Campos opcionales descriptivos (si luego los necesitas)
    private String categoriaNombre;
    private String medidaNombre;
}
