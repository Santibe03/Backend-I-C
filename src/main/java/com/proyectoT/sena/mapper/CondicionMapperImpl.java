package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.CondicionDTO;
import com.proyectoT.sena.models.Condicion;

@Component
public class CondicionMapperImpl implements CondicionMapper {

    @Override
    public CondicionDTO toDto(Condicion entity) {
        if (entity == null) return null;

        CondicionDTO dto = new CondicionDTO();
        dto.setId(entity.getId());
        dto.setConditionName(entity.getConditionName());

        return dto;
    }

    @Override
    public Condicion toEntity(CondicionDTO dto) {
        if (dto == null) return null;

        Condicion entity = new Condicion();
        entity.setId(dto.getId());
        entity.setConditionName(dto.getConditionName());

        return entity;
    }
}
