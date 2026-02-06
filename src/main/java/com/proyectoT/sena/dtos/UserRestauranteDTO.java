package com.proyectoT.sena.dtos;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRestauranteDTO {

    private Long userId;
    private Long restauranteId;
    private String nombreRestaurante;
    private Boolean esAdministrador;
    private Instant fechaAsociacion;
    private Boolean activo;
}
