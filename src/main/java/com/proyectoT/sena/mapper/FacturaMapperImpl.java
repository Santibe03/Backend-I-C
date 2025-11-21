package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.FacturaDTO;
import com.proyectoT.sena.models.Factura;

@Component
public class FacturaMapperImpl implements FacturaMapper {

    @Override
    public FacturaDTO toDTO(Factura entity) {
        if (entity == null) return null;

        FacturaDTO dto = new FacturaDTO();

        dto.setId(entity.getId());
        dto.setTotal(entity.getTotal());
        dto.setDate(entity.getDate());

        // Relaciones
        if (entity.getPerson() != null) {
            dto.setPersonId(entity.getPerson().getId());
            dto.setPersonNombre(entity.getPerson().getFirstName() + " " + entity.getPerson().getFirstLastName());
        }

        // Cantidad de productos
        if (entity.getProductBills() != null) {
            dto.setCantidadProductos(entity.getProductBills().size());
        }

        return dto;
    }

    @Override
    public Factura toEntity(FacturaDTO dto) {
        if (dto == null) return null;

        Factura entity = new Factura();

        entity.setId(dto.getId());
        entity.setTotal(dto.getTotal());
        entity.setDate(dto.getDate());

        // Las relaciones se asignarán en el Service (Person, ProductoFactura)

        return entity;
    }

    @Override
    public void updateEntityFromDTO(FacturaDTO dto, Factura entity) {
        if (dto == null || entity == null) return;

        entity.setTotal(dto.getTotal());
        entity.setDate(dto.getDate());

        // La relación person se actualiza desde el service
    }
}
