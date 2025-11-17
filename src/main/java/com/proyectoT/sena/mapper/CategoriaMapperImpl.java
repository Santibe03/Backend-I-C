package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.CategoriaDTO;
import com.proyectoT.sena.models.Categoria;

@Component
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setCatNam(dto.getCatNam());

        return categoria;
    }

    @Override
    public CategoriaDTO toDto(Categoria entity) {
        if (entity == null) {
            return null;
        }

        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(entity.getId());
        dto.setCatNam(entity.getCatNam());

        if (entity.getInsumos() != null) {
            dto.setInsumosCount(entity.getInsumos().size());
        }

        return dto;
    }
}
