package com.proyectoT.sena.security;

import com.proyectoT.sena.service.JwtService;
import com.proyectoT.sena.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // LOGS DE DEPURACIÓN (Bórralos cuando ya funcione)
        String path = request.getRequestURI();
        System.out.println("--- INTENTANDO ACCESO A: " + path + " ---");

        final String authHeader = request.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("❌ No hay header Authorization o no empieza con Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            System.out.println("🔍 Token recibido (primeros 10 chars): " + jwt.substring(0, Math.min(jwt.length(), 10)) + "...");
            
            final String userEmail = jwtService.extraerUsername(jwt);
            System.out.println("👤 Usuario extraído del token: " + userEmail);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                
                if (jwtService.validarToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("✅ Autenticación exitosa. Usuario añadido al contexto.");
                } else {
                    System.out.println("❌ Token inválido o expirado.");
                }
            }
        } catch (Exception e) {
            System.out.println("☠️ ERROR PROCESANDO TOKEN: " + e.getMessage());
            e.printStackTrace(); // Esto nos dirá si la firma está mal
        }
        
        filterChain.doFilter(request, response);
    }
}