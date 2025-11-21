package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import com.proyectoT.sena.dtos.OrdenProductoDTO;

public interface OrdenProductoService {

    OrdenProductoDTO save(OrdenProductoDTO dto);

    Optional<OrdenProductoDTO> findOne(Long id);

    List<OrdenProductoDTO> findAll();

    void delete(Long id);
}
