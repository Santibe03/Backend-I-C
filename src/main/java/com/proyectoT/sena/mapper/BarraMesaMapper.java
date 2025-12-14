package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;
import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.models.BarraMesa;

@Component
public class BarraMesaMapper {

    public BarraMesaDTO toDto(BarraMesa entity) {
        if (entity == null) {
            return null;
        }
        BarraMesaDTO dto = new BarraMesaDTO();
        dto.setId(entity.getId());
        dto.setAvailability(entity.getAvailability());
        dto.setShare(entity.getShare());
        return dto;
    }

    public BarraMesa toEntity(BarraMesaDTO dto) {
        if (dto == null) {
            return null;
        }
        BarraMesa entity = new BarraMesa();
        entity.setId(dto.getId());
        entity.setAvailability(dto.getAvailability());
        entity.setShare(dto.getShare());
        return entity;
    }
}
