package com.proyectoT.sena.service;

import java.util.List;
import com.proyectoT.sena.dtos.ProductoDTO;

public interface ProductoService {

    ProductoDTO save(ProductoDTO dto);

    ProductoDTO update(Long id, ProductoDTO dto);

    void delete(Long id);

    ProductoDTO findById(Long id);

    List<ProductoDTO> findAll();
}
