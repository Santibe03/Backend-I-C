package com.proyectoT.sena.service;

import com.proyectoT.sena.dtos.CondicionDTO;
import java.util.List;
import java.util.Optional;

public interface CondicionService {

    CondicionDTO save(CondicionDTO dto);

    CondicionDTO update(CondicionDTO dto);

    Optional<CondicionDTO> findOne(Long id);

    List<CondicionDTO> findAll();

    void delete(Long id);
}
