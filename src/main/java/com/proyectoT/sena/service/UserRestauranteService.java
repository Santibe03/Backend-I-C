package com.proyectoT.sena.service;

import java.util.List;

import com.proyectoT.sena.dtos.UserRestauranteDTO;

public interface UserRestauranteService {

    UserRestauranteDTO asociarUsuario(Long userId, Long restauranteId, Boolean esAdministrador);

    void removerUsuario(Long userId, Long restauranteId);

    UserRestauranteDTO cambiarPermisos(Long userId, Long restauranteId, Boolean esAdministrador);

    List<UserRestauranteDTO> obtenerRestaurantesPorUsuario(Long userId);

    List<UserRestauranteDTO> obtenerUsuariosPorRestaurante(Long restauranteId);
}
