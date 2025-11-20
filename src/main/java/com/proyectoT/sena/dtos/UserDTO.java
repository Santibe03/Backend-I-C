package com.proyectoT.sena.dtos;

import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String password;

    private boolean activated;
    private String langKey;
    private String imageUrl;
    private Instant resetDate;

    private Set<String> authorities;
}


