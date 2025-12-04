// com.proyectoT.sena.dtos.LoginResponse

package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // Genera el constructor con todos los argumentos
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String email; // o username
    private String role;
}