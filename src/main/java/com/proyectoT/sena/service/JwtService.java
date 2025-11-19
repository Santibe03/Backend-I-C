package com.proyectoT.sena.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generarToken(UserDetails userDetails);

    String extraerUsername(String token);

    boolean validarToken(String token, UserDetails userDetails);
}

