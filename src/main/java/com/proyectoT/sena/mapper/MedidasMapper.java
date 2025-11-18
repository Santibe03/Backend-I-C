package com.proyectoT.sena.mapper;

import java.util.List;
import com.proyectoT.sena.dtos.MedidasDTO;
import com.proyectoT.sena.models.Medidas;

public interface MedidasMapper {

    MedidasDTO toDto(Medidas entity);

    Medidas toEntity(MedidasDTO dto);

    List<MedidasDTO> toDtoList(List<Medidas> list);

    List<Medidas> toEntityList(List<MedidasDTO> list);
}
