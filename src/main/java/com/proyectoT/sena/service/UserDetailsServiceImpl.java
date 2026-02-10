package com.proyectoT.sena.service;

import com.proyectoT.sena.models.User;
import com.proyectoT.sena.repositoryes.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional // Necesario para cargar la relación LAZY de userAuthorities
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Cargar los roles REALES del usuario desde la tabla app_user_authority
        var authorities = user.getUserAuthorities()
                .stream()
                .map(ua -> new SimpleGrantedAuthority(ua.getAuthority().getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.isActivated(),
                true, true, true,
                authorities // Ahora SÍ carga los roles reales
        );
    }
}
