package com.proyectoT.sena.service;

import java.util.List;
import com.proyectoT.sena.dtos.OrdenDTO;

public interface OrdenService {
    OrdenDTO create(OrdenDTO dto);
    OrdenDTO update(OrdenDTO dto);
    List<OrdenDTO> findAll();
    OrdenDTO findById(Long id);
    void delete(Long id);
}
