package com.proyectoT.sena.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.proyectoT.sena.dtos.ProductoDTO;

public interface ProductoService {

    ProductoDTO save(ProductoDTO dto, MultipartFile image);

    ProductoDTO update(Long id, ProductoDTO dto, MultipartFile image);

    void delete(Long id);

    ProductoDTO findById(Long id);

    List<ProductoDTO> findAll();
}
