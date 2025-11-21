package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import com.proyectoT.sena.dtos.InsumosProductoDTO;

public interface InsumosProductoService {

    InsumosProductoDTO save(InsumosProductoDTO dto);

    List<InsumosProductoDTO> findAll();

    Optional<InsumosProductoDTO> findOne(Long id);

    void delete(Long id);
}
