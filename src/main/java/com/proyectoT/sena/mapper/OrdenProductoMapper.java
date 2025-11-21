package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.OrdenProductoDTO;
import com.proyectoT.sena.models.OrdenProducto;

public interface OrdenProductoMapper {

    OrdenProductoDTO toDto(OrdenProducto entity);

    OrdenProducto toEntity(OrdenProductoDTO dto);
}

