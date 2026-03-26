package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.proyectoT.sena.models.Medida;
import com.proyectoT.sena.models.enums.CategoriaEnum;

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
    // Relaciones
    private CategoriaEnum categoria;
    private Medida medida;

    private Long restauranteId;
}
