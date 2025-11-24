package com.proyectoT.sena.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;   // usa 'login' según tu entidad
    private String password;
}
