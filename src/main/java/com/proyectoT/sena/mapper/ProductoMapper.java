package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.ProductoDTO;
import com.proyectoT.sena.models.Producto;

public interface ProductoMapper {
    ProductoDTO toDto(Producto entity);
    Producto toEntity(ProductoDTO dto, byte[] imageBytes, String contentType);
}
