package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import com.proyectoT.sena.dtos.BarraMesaDTO;

public interface BarraMesaService {

    BarraMesaDTO save(BarraMesaDTO dto);

    BarraMesaDTO update(BarraMesaDTO dto);

    Optional<BarraMesaDTO> findOne(Long id);

    List<BarraMesaDTO> findAll();

    void delete(Long id);
}

