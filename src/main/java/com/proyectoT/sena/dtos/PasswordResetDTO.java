package com.proyectoT.sena.dtos;

import lombok.Data;

@Data
public class PasswordResetDTO {
    private String key;
    private String newPassword;
}
