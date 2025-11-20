package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.models.BarraMesa;

@Component
public class BarraMesaMapperImpl implements BarraMesaMapper {

    @Override
    public BarraMesaDTO toDto(BarraMesa entity) {
        if (entity == null) return null;

        BarraMesaDTO dto = new BarraMesaDTO();
        dto.setId(entity.getId());
        dto.setAvalabily(entity.getAvalabily());

        return dto;
    }

    @Override
    public BarraMesa toEntity(BarraMesaDTO dto) {
        if (dto == null) return null;

        BarraMesa entity = new BarraMesa();
        entity.setId(dto.getId());
        entity.setAvalabily(dto.getAvalabily());

        return entity;
    }
}

