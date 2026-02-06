package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.UserRestauranteDTO;
import com.proyectoT.sena.models.UserRestaurante;

@Component
public class UserRestauranteMapper {

    public UserRestauranteDTO toDTO(UserRestaurante entity) {
        if (entity == null) {
            return null;
        }

        UserRestauranteDTO dto = new UserRestauranteDTO();

        if (entity.getId() != null) {
            dto.setUserId(entity.getId().getUserId());
            dto.setRestauranteId(entity.getId().getRestauranteId());
        }

        dto.setEsAdministrador(entity.getEsAdministrador());
        dto.setFechaAsociacion(entity.getFechaAsociacion());
        dto.setActivo(entity.getActivo());

        if (entity.getRestaurante() != null) {
            dto.setNombreRestaurante(entity.getRestaurante().getNombre());
        }

        return dto;
    }

    public UserRestaurante toEntity(UserRestauranteDTO dto) {
        if (dto == null) {
            return null;
        }

        UserRestaurante entity = new UserRestaurante();
        entity.setEsAdministrador(dto.getEsAdministrador());
        entity.setFechaAsociacion(dto.getFechaAsociacion());
        entity.setActivo(dto.getActivo());

        // El id compuesto y las relaciones se setean en el servicio

        return entity;
    }
}
