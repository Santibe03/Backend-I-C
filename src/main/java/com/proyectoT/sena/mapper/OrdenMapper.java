package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.OrdenDTO;
import com.proyectoT.sena.models.Orden;

public interface OrdenMapper {
    OrdenDTO toDto(Orden entity);
    Orden toEntity(OrdenDTO dto);
    void updateEntityFromDTO(OrdenDTO dto, Orden entity);
}
