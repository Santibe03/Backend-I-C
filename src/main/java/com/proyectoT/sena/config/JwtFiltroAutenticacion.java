package com.proyectoT.sena.config;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.proyectoT.sena.service.JwtService;
import com.proyectoT.sena.service.CustomUserDetailsService;

@Component
public class JwtFiltroAutenticacion extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        // Leer el encabezado Authorization
        String authHeader = request.getHeader("Authorization");

        // Si no hay token o no empieza con "Bearer ", continuar sin autenticación
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraer el JWT
        String jwt = authHeader.substring(7);
        String username = jwtService.extraerUsername(jwt); // <-- NOMBRE CORRECTO

        // Validar si el usuario NO está autenticado aún
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Cargar el usuario desde la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Validar el token
            if (jwtService.validarToken(jwt, userDetails)) {

                // Crear el objeto de autenticación
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Registrar al usuario como autenticado
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continuar con los filtros
        filterChain.doFilter(request, response);
    }
}

