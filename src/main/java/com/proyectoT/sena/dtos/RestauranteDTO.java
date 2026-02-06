package com.proyectoT.sena.dtos;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String contacto;
    private Instant fechaCreacion;
    private Boolean activo;
    private Long usuarioCreadorId;
}
