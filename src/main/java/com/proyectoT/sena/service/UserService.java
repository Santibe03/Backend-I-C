package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import com.proyectoT.sena.dtos.UserDTO;

public interface UserService {

    UserDTO save(UserDTO dto);

    UserDTO update(UserDTO dto);

    Optional<UserDTO> findOne(Long id);

    List<UserDTO> findAll();

    void delete(Long id);
}

