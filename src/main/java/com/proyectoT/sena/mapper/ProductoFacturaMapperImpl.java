package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.ProductoFacturaDTO;
import com.proyectoT.sena.models.ProductoFactura;

@Component
public class ProductoFacturaMapperImpl implements ProductoFacturaMapper {

    @Override
    public ProductoFacturaDTO toDto(ProductoFactura entity) {
        if (entity == null) {
            return null;
        }

        ProductoFacturaDTO dto = new ProductoFacturaDTO();

        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setPrice(entity.getPrice());

        // PRODUCTO
        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getName());
        }

        // FACTURA
        if (entity.getBill() != null) {
            dto.setBillId(entity.getBill().getId());
        }

        return dto;
    }

    @Override
    public ProductoFactura toEntity(ProductoFacturaDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductoFactura entity = new ProductoFactura();

        entity.setId(dto.getId());
        entity.setAmount(dto.getAmount());
        entity.setPrice(dto.getPrice());

        // Relaciones se asignan después en el service
        entity.setProduct(null);
        entity.setBill(null);

        return entity;
    }
}
