package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import com.proyectoT.sena.dtos.IngresoInsumoDTO;

public interface IngresoInsumoService {

    IngresoInsumoDTO save(IngresoInsumoDTO dto);

    List<IngresoInsumoDTO> findAll();

    Optional<IngresoInsumoDTO> findOne(Long id);

    void delete(Long id);
}
