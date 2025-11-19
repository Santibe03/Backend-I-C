package com.proyectoT.sena.service;

import com.proyectoT.sena.dtos.InsumoDTO;

import java.util.List;

public interface InsumoService {

    List<InsumoDTO> findAll();

    InsumoDTO findById(Long id);

    InsumoDTO save(InsumoDTO dto);

    InsumoDTO update(Long id, InsumoDTO dto);

    void delete(Long id);
}
