package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.ProductoDTO;
import com.proyectoT.sena.models.Producto;

@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoDTO toDto(Producto entity) {
        if (entity == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setImageUrl(entity.getImageUrl());
        dto.setDescription(entity.getDescription());

        if (entity.getRestaurante() != null) {
            dto.setRestauranteId(entity.getRestaurante().getId());
        }

        return dto;
    }

    @Override
    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }

        Producto entity = new Producto();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setImageUrl(dto.getImageUrl());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}
