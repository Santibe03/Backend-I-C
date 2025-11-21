package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.OrdenProductoDTO;
import com.proyectoT.sena.models.OrdenProducto;

@Component
public class OrdenProductoMapperImpl implements OrdenProductoMapper {

    @Override
    public OrdenProductoDTO toDto(OrdenProducto entity) {
        if (entity == null) {
            return null;
        }

        OrdenProductoDTO dto = new OrdenProductoDTO();

        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setPrice(entity.getPrice());

        // PRODUCTO
        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getName());
        }

        // ORDEN
        if (entity.getOrder() != null) {
            dto.setOrderId(entity.getOrder().getId());
            // no existe orderCode → no se incluye
        }

        return dto;
    }

    @Override
    public OrdenProducto toEntity(OrdenProductoDTO dto) {
        if (dto == null) {
            return null;
        }

        OrdenProducto entity = new OrdenProducto();

        entity.setId(dto.getId());
        entity.setAmount(dto.getAmount());
        entity.setPrice(dto.getPrice());

        // Relaciones se asignan en el service
        entity.setProduct(null);
        entity.setOrder(null);

        return entity;
    }
}
