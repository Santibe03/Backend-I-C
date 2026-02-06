package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private UserDTO user;
    private PersonDTO person;

    // Nuevos campos para gestión de restaurantes
    private String tipoAsociacion; // "CREAR_RESTAURANTE", "ASOCIAR_RESTAURANTE", "NINGUNA"
    private RestauranteDTO nuevoRestaurante; // Si crea restaurante
    private Long restauranteIdAsociar; // Si se asocia a existente
    private Boolean solicitarComoAdmin; // Si solicita permisos de admin (para otros admins)
}
