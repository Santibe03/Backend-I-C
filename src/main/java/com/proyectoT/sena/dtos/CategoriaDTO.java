package com.proyectoT.sena.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String catNam;

    // Opcional: cantidad de insumos asociados (útil en listados)
    private Integer insumosCount;
}
