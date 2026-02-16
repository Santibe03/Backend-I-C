package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.InsumoDTO;
import com.proyectoT.sena.models.Insumo;
import com.proyectoT.sena.models.Medida;

@Component
public class InsumoMapperImpl implements InsumoMapper {

    @Override
    public InsumoDTO toDTO(Insumo entity) {
        if (entity == null)
            return null;

        InsumoDTO dto = new InsumoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getInputName());
        dto.setMarca(entity.getBrand());
        dto.setCantidad(entity.getAmount());

        if (entity.getCategory() != null) {
            dto.setCategoria(entity.getCategory());
        }

        dto.setMedida(entity.getMeasure());

        if (entity.getRestaurante() != null) {
            dto.setRestauranteId(entity.getRestaurante().getId());
        }

        return dto;
    }

    @Override
    public Insumo toEntity(InsumoDTO dto) {
        if (dto == null)
            return null;

        Insumo entity = new Insumo();

        entity.setId(dto.getId());
        entity.setInputName(dto.getNombre());
        entity.setBrand(dto.getMarca());
        entity.setAmount(dto.getCantidad());

        if (dto.getCategoria() != null) {
            entity.setCategory(dto.getCategoria());
        }

        entity.setMeasure(dto.getMedida());

        return entity;
    }
}
