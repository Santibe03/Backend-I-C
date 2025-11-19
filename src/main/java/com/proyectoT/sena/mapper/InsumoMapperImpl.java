package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.InsumoDTO;
import com.proyectoT.sena.models.Categoria;
import com.proyectoT.sena.models.Insumo;
import com.proyectoT.sena.models.Medidas;

@Component
public class InsumoMapperImpl implements InsumoMapper {

    @Override
    public InsumoDTO toDTO(Insumo entity) {
        if (entity == null) return null;

        InsumoDTO dto = new InsumoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getInputName());
        dto.setMarca(entity.getBrand());
        dto.setCantidad(entity.getAmount());

        if (entity.getCategory() != null) {
            dto.setCategoriaId(entity.getCategory().getId());
            dto.setCategoriaNombre(entity.getCategory().getCatNam());
        }

        if (entity.getMeasure() != null) {
            dto.setMedidaId(entity.getMeasure().getId());
            dto.setMedidaNombre(entity.getMeasure().getMedNam());
        }

        return dto;
    }

    @Override
    public Insumo toEntity(InsumoDTO dto) {
        if (dto == null) return null;

        Insumo entity = new Insumo();

        entity.setId(dto.getId());
        entity.setInputName(dto.getNombre());
        entity.setBrand(dto.getMarca());
        entity.setAmount(dto.getCantidad());

        if (dto.getCategoriaId() != null) {
            Categoria categoria = new Categoria();
            categoria.setId(dto.getCategoriaId());
            entity.setCategory(categoria);
        }

        if (dto.getMedidaId() != null) {
            Medidas medida = new Medidas();
            medida.setId(dto.getMedidaId());
            entity.setMeasure(medida);
        }

        return entity;
    }
}
