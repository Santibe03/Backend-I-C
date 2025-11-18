package com.proyectoT.sena.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.UserDTO;
import com.proyectoT.sena.models.User;


@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setActivated(user.isActivated());
        dto.setLangKey(user.getLangKey());
        dto.setImageUrl(user.getImageUrl());
        dto.setResetDate(user.getResetDate());

        dto.setAuthorities(
            user.getUserAuthorities()
                .stream()
                .map(ua -> ua.getAuthority().getName())
                .collect(Collectors.toSet())
        );

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
        user.setActivated(dto.isActivated());
        user.setLangKey(dto.getLangKey());
        user.setImageUrl(dto.getImageUrl());
        user.setResetDate(dto.getResetDate());

        return user;
    }
}

