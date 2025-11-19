package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import com.proyectoT.sena.dtos.MedidasDTO;

public interface MedidasService {

    MedidasDTO save(MedidasDTO dto);

    MedidasDTO update(MedidasDTO dto);

    Optional<MedidasDTO> findOne(Long id);

    List<MedidasDTO> findAll();

    void delete(Long id);
}

