package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import com.proyectoT.sena.dtos.IngresoDTO;

public interface IngresoService {

    IngresoDTO save(IngresoDTO dto);

    IngresoDTO update(IngresoDTO dto);

    Optional<IngresoDTO> findOne(Long id);

    List<IngresoDTO> findAll();

    void delete(Long id);
}
