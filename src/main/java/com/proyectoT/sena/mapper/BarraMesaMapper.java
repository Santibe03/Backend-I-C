package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.models.BarraMesa;

public interface BarraMesaMapper {

    BarraMesaDTO toDto(BarraMesa entity);

    BarraMesa toEntity(BarraMesaDTO dto);
}

