package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngresoInsumoDTO {

    private Long id;
    private Integer amount;

    private Long inputId;
    private String inputName;

    private Long measureId;
    private String measureName;

    private Long incomeId;
}
