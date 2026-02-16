package com.proyectoT.sena.dtos;

import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class PasswordResetDTO {
    private String key;

    @Size(max = 12, message = "La contraseña no puede tener más de 12 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).*$", message = "La contraseña debe tener al menos una mayúscula, un número y un caracter especial")
    private String newPassword;
}
