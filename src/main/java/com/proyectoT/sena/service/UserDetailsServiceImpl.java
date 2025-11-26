package com.proyectoT.sena.service;

import com.proyectoT.sena.models.User;
import com.proyectoT.sena.repositoryes.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscamos usuario por 'login' (que es tu nombre de usuario)
        User user = userRepository.findOneByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Retornamos un objeto User de Spring Security (diferente a tu modelo User)
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.isActivated(),
                true, true, true,
                new ArrayList<>() // Aquí irían los roles/authorities si los usaras
        );
    }
}
