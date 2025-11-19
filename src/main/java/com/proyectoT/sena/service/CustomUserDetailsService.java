package com.proyectoT.sena.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyectoT.sena.models.User;
import com.proyectoT.sena.repositoryes.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findOneByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + login));

        // Convertir Authorities (roles) si las necesitas
        var authorities = user.getUserAuthorities()
                .stream()
                .map(a -> a.getAuthority().getName()) // Role name
                .map(role -> "ROLE_" + role)          // Spring exige formato ROLE_X
                .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())      // login como identificador
                .password(user.getPassword())   // password_hash
                .authorities(authorities)       // authorities reales
                .disabled(!user.isActivated())  // solo usuarios activados
                .build();
    }
}
