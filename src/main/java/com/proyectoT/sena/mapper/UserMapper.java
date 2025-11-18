package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.UserDTO;
import com.proyectoT.sena.models.User;

public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO dto);
}
