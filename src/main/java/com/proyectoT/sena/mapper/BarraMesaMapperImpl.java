package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.models.BarraMesa;

@Component
public class BarraMesaMapperImpl implements BarraMesaMapper {

    @Override
    public BarraMesa toEntity(BarraMesaDTO dto) {
        if (dto == null) {
            return null;
        }

        BarraMesa mesa = new BarraMesa();
        mesa.setId(dto.getId());
        mesa.setAvalabily(dto.getAvalabily());
        mesa.setShare(dto.getShare());

        // Las relaciones NO se cargan aquí (solo el standalone)
    
        return mesa;
    }

    @Override
    public BarraMesaDTO toDto(BarraMesa entity) {
        if (entity == null) {
            return null;
        }

        BarraMesaDTO dto = new BarraMesaDTO();
        dto.setId(entity.getId());
        dto.setAvalabily(entity.getAvalabily());
        dto.setShare(entity.getShare());

        // Campos de resumen opcionales
        dto.setTotalOrders(
            entity.getOrders() != null ? entity.getOrders().size() : 0
        );

        dto.setTotalReservations(
            entity.getReservations() != null ? entity.getReservations().size() : 0
        );

        return dto;
    }
}
