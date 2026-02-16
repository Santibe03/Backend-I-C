package com.proyectoT.sena.dtos;

import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;

    // NUEVO: password solo para recibir del cliente
    @Size(max = 60, message = "La contraseña no puede tener más de 60 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).*$", message = "La contraseña debe tener al menos una mayúscula, un número y un caracter especial")
    private String password;

    private boolean activated;
    private String langKey;
    private String imageUrl;
    private Instant resetDate;

    private Set<String> authorities;
}
