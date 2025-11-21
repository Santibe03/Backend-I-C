package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.IngresoInsumoDTO;
import com.proyectoT.sena.models.IngresoInsumo;

public interface IngresoInsumoMapper {

    IngresoInsumoDTO toDto(IngresoInsumo entity);

    IngresoInsumo toEntity(IngresoInsumoDTO dto);
}
