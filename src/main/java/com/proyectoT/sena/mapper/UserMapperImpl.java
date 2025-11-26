package com.proyectoT.sena.mapper;

import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.UserDTO;
import com.proyectoT.sena.models.User;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());

        // Nunca se debe enviar la contraseña al front
        dto.setPassword(null);

        dto.setActivated(user.isActivated());
        dto.setLangKey(user.getLangKey());
        dto.setImageUrl(user.getImageUrl());
        dto.setResetDate(user.getResetDate());

        // Evita null pointer si no hay authorities
        if (user.getUserAuthorities() != null) {
            dto.setAuthorities(
                user.getUserAuthorities()
                    .stream()
                    .map(ua -> ua.getAuthority().getName())
                    .collect(Collectors.toSet())
            );
        } else {
            dto.setAuthorities(Collections.emptySet());
        }

        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        // Aquí sí guardamos la contraseña
        user.setPassword(dto.getPassword());

        user.setActivated(dto.isActivated());
        user.setLangKey(dto.getLangKey());
        user.setImageUrl(dto.getImageUrl());
        user.setResetDate(dto.getResetDate());

        // OJO: Authorities NO se asignan aquí  
        // porque eso se hace en el servicio para evitar bugs y duplicados

        return user;
    }
}


