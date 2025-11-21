package com.proyectoT.sena.service;

import java.util.List;
import com.proyectoT.sena.dtos.FacturaDTO;

public interface FacturaService {

    FacturaDTO create(FacturaDTO dto);

    FacturaDTO update(FacturaDTO dto);

    List<FacturaDTO> findAll();

    FacturaDTO findById(Long id);

    void delete(Long id);
}
