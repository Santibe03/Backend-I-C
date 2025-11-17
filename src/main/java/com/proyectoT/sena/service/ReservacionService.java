package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import com.proyectoT.sena.dtos.ReservacionDTO;

public interface ReservacionService {

    ReservacionDTO save(ReservacionDTO dto);

    ReservacionDTO update(ReservacionDTO dto);

    Optional<ReservacionDTO> findOne(Long id);

    List<ReservacionDTO> findAll();

    void delete(Long id);
}

