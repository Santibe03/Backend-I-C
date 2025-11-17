package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.models.BarraMesa;

public interface BarraMesaMapper {

    BarraMesa toEntity(BarraMesaDTO dto);

    BarraMesaDTO toDto(BarraMesa entity);
}

