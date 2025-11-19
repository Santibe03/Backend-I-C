package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.InsumoDTO;
import com.proyectoT.sena.models.Insumo;

public interface InsumoMapper {

    InsumoDTO toDTO(Insumo entity);

    Insumo toEntity(InsumoDTO dto);
}

