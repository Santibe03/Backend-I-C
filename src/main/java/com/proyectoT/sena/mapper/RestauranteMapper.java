package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.RestauranteDTO;
import com.proyectoT.sena.models.Restaurante;

@Component
public class RestauranteMapper {

    public RestauranteDTO toDTO(Restaurante entity) {
        if (entity == null) {
            return null;
        }

        RestauranteDTO dto = new RestauranteDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDireccion(entity.getDireccion());
        dto.setContacto(entity.getContacto());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setActivo(entity.getActivo());

        if (entity.getUsuarioCreador() != null) {
            dto.setUsuarioCreadorId(entity.getUsuarioCreador().getId());
        }

        return dto;
    }

    public Restaurante toEntity(RestauranteDTO dto) {
        if (dto == null) {
            return null;
        }

        Restaurante entity = new Restaurante();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDireccion(dto.getDireccion());
        entity.setContacto(dto.getContacto());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setActivo(dto.getActivo());

        // El usuario creador se setea en el servicio

        return entity;
    }
}
