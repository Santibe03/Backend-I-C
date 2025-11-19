package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import com.proyectoT.sena.dtos.CategoriaDTO;

public interface CategoriaService {

    CategoriaDTO save(CategoriaDTO dto);

    CategoriaDTO update(CategoriaDTO dto);

    Optional<CategoriaDTO> findOne(Long id);

    List<CategoriaDTO> findAll();

    void delete(Long id);
}
