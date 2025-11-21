package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.InsumosProductoDTO;
import com.proyectoT.sena.models.InsumosProducto;

public interface InsumosProductoMapper {

    InsumosProductoDTO toDto(InsumosProducto entity);

    InsumosProducto toEntity(InsumosProductoDTO dto);
}

