package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import com.proyectoT.sena.dtos.ProductoFacturaDTO;

public interface ProductoFacturaService {

    ProductoFacturaDTO save(ProductoFacturaDTO dto);

    Optional<ProductoFacturaDTO> findOne(Long id);

    List<ProductoFacturaDTO> findAll();

    void delete(Long id);
}
