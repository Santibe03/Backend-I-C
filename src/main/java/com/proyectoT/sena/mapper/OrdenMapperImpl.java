package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;
import com.proyectoT.sena.dtos.OrdenDTO;
import com.proyectoT.sena.models.Orden;

@Component
public class OrdenMapperImpl implements OrdenMapper {

    @Override
    public OrdenDTO toDto(Orden entity) {
        if (entity == null) return null;

        OrdenDTO dto = new OrdenDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());

        if (entity.getBarTable() != null) {
            dto.setBarraMesaId(entity.getBarTable().getId());
        }
        

        if (entity.getCondition() != null) {
            dto.setCondicionId(entity.getCondition().getId());
            // Ajusta getNombre() al getter real de Condicion
             dto.setCondicionNombre(entity.getCondition().getConditionName());
        }

        if (entity.getOrderProducts() != null) {
            dto.setCantidadProductos(entity.getOrderProducts().size());
        } else {
            dto.setCantidadProductos(0);
        }

        return dto;
    }

    @Override
    public Orden toEntity(OrdenDTO dto) {
        if (dto == null) return null;

        Orden entity = new Orden();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        // relaciones se asignan en el service por seguridad
        return entity;
    }

    @Override
    public void updateEntityFromDTO(OrdenDTO dto, Orden entity) {
        if (dto == null || entity == null) return;
        entity.setDate(dto.getDate());
        // relaciones se actualizan en el service
    }
}
