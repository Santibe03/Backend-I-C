package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.proyectoT.sena.models.Medida;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngresoInsumoDTO {

    private Long id;
    private Integer amount;

    private Long inputId;
    private String inputName;

    private com.proyectoT.sena.models.Medida measure;

    private Long incomeId;
}
