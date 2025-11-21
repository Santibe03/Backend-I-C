package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.ProductoFacturaDTO;
import com.proyectoT.sena.models.ProductoFactura;

public interface ProductoFacturaMapper {

    ProductoFacturaDTO toDto(ProductoFactura entity);

    ProductoFactura toEntity(ProductoFacturaDTO dto);
}
