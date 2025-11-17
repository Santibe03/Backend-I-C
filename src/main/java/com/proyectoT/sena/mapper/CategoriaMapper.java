package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.CategoriaDTO;
import com.proyectoT.sena.models.Categoria;

public interface CategoriaMapper {

    Categoria toEntity(CategoriaDTO dto);

    CategoriaDTO toDto(Categoria entity);
}

