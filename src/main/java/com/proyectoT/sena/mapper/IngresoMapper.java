package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.IngresoDTO;
import com.proyectoT.sena.models.Ingresos;

@Component
public interface IngresoMapper {

    Ingresos toEntity(IngresoDTO dto);

    IngresoDTO toDto(Ingresos entity);
}
