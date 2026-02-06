package com.proyectoT.sena.service;

import java.util.List;

import com.proyectoT.sena.dtos.RestauranteDTO;

public interface RestauranteService {

    RestauranteDTO crear(RestauranteDTO dto, Long usuarioCreadorId);

    RestauranteDTO actualizar(Long id, RestauranteDTO dto);

    RestauranteDTO obtenerPorId(Long id);

    List<RestauranteDTO> listar();

    List<RestauranteDTO> buscarPorNombre(String nombre);

    List<RestauranteDTO> listarPorUsuario(Long userId);

    void desactivar(Long id);
}
