package com.proyectoT.sena.mapper;

import com.proyectoT.sena.models.Condicion;
import com.proyectoT.sena.dtos.CondicionDTO;

public interface CondicionMapper {

    CondicionDTO toDto(Condicion entity);

    Condicion toEntity(CondicionDTO dto);
}
