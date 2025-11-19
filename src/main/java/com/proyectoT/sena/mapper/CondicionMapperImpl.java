package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;
import com.proyectoT.sena.models.Condicion;
import com.proyectoT.sena.dtos.CondicionDTO;

@Component
public class CondicionMapperImpl implements CondicionMapper {

    @Override
    public CondicionDTO toDto(Condicion entity) {
        if (entity == null) return null;

        CondicionDTO dto = new CondicionDTO();
        dto.setId(entity.getId());
        dto.setConditionName(entity.getConditionName());

        // Datos derivados opcionales
        dto.setTotalOrders(entity.getOrders() != null ? entity.getOrders().size() : 0);
        dto.setTotalReservations(entity.getReservations() != null ? entity.getReservations().size() : 0);

        return dto;
    }

    @Override
    public Condicion toEntity(CondicionDTO dto) {
        if (dto == null) return null;

        Condicion entity = new Condicion();
        entity.setId(dto.getId());
        entity.setConditionName(dto.getConditionName());

        // No seteamos orders ni reservations
        return entity;
    }
}

