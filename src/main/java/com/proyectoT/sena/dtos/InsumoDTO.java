package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.proyectoT.sena.models.Medida;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsumoDTO {

    private Long id;

    private String nombre; // inputName
    private String marca;
    private Double cantidad; // current_stock

    // Relaciones representadas por ID
    // Relaciones
    private Long categoriaId;
    private Medida medida;

    // Campos opcionales
    private String categoriaNombre;
}
