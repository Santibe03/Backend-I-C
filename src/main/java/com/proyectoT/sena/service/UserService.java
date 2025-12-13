package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import com.proyectoT.sena.dtos.RegisterRequestDTO;
import com.proyectoT.sena.dtos.UserDTO;
import com.proyectoT.sena.models.User;

public interface UserService {
    UserDTO save(UserDTO dto);
    UserDTO update(Long id, UserDTO dto);
    UserDTO findById(Long id);
    List<UserDTO> findAll();
    void delete(Long id);

    // --- MÉTODO PARA REGISTRO COMPLETO ---
    UserDTO registerUser(RegisterRequestDTO dto);

    // --- NUEVOS MÉTODOS PARA RECUPERACIÓN ---
    Optional<User> requestPasswordReset(String mail);
    User completePasswordReset(String newPassword, String key);
}
