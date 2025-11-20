package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.CondicionDTO;
import com.proyectoT.sena.models.Condicion;

public interface CondicionMapper {

    CondicionDTO toDto(Condicion entity);

    Condicion toEntity(CondicionDTO dto);
}
