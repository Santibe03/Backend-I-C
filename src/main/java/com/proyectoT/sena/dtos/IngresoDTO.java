package com.proyectoT.sena.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngresoDTO implements Serializable {

    private Long id;

    private LocalDate fecha;

    @Size(max = 255)
    private String detalles;

    @NotNull
    private PersonDTO person;
}

