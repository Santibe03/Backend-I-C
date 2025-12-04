package com.proyectoT.sena.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importante para lazy loading

import com.proyectoT.sena.models.User;
import com.proyectoT.sena.repositoryes.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional // <--- Agrega esto para evitar errores al cargar authorities
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findOneByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + login));

        var authorities = user.getUserAuthorities()
                .stream()
                .map(a -> a.getAuthority().getName()) 
                // .map(role -> "ROLE_" + role) <--- ELIMINADO. Asumimos que en BD ya dice "ROLE_ADMINISTRADOR"
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .authorities(authorities)
                .disabled(!user.isActivated())
                .build();
    }
}