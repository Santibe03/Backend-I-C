package com.proyectoT.sena.service;

import com.proyectoT.sena.dtos.InsumoDTO;

import java.util.List;
import java.util.Optional;

public interface InsumoService {

    InsumoDTO save(InsumoDTO dto);

    InsumoDTO update(InsumoDTO dto);

    Optional<InsumoDTO> findOne(Long id);

    List<InsumoDTO> findAll();

    void delete(Long id);
}
