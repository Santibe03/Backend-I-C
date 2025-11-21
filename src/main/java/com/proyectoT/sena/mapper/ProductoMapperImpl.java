package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;
import com.proyectoT.sena.dtos.ProductoDTO;
import com.proyectoT.sena.models.Producto;

@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoDTO toDto(Producto entity) {
        if (entity == null) return null;

        ProductoDTO dto = new ProductoDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());

        // La imagen NO se envía en el DTO (muy pesada)
        return dto;
    }

    @Override
    public Producto toEntity(ProductoDTO dto, byte[] imageBytes, String contentType) {
        Producto entity = new Producto();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setProductImage(imageBytes);
        entity.setProductImageContentType(contentType);

        return entity;
    }
}
