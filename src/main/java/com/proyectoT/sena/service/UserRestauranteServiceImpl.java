package com.proyectoT.sena.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.UserRestauranteDTO;
import com.proyectoT.sena.mapper.UserRestauranteMapper;
import com.proyectoT.sena.models.Restaurante;
import com.proyectoT.sena.models.User;
import com.proyectoT.sena.models.UserRestaurante;
import com.proyectoT.sena.models.UserRestauranteId;
import com.proyectoT.sena.repositoryes.RestauranteRepository;
import com.proyectoT.sena.repositoryes.UserRepository;
import com.proyectoT.sena.repositoryes.UserRestauranteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRestauranteServiceImpl implements UserRestauranteService {

    private final UserRestauranteRepository userRestauranteRepository;
    private final UserRepository userRepository;
    private final RestauranteRepository restauranteRepository;
    private final UserRestauranteMapper userRestauranteMapper;

    @Override
    public UserRestauranteDTO asociarUsuario(Long userId, Long restauranteId, Boolean esAdministrador) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

        // Verificar si ya existe la asociación
        UserRestauranteId id = new UserRestauranteId(userId, restauranteId);
        UserRestaurante existing = userRestauranteRepository.findById(id).orElse(null);

        if (existing != null) {
            // Si se quiere activar como admin, verificar que no exceda el límite de 2
            if (esAdministrador && !existing.getEsAdministrador()) {
                if (userRestauranteRepository
                        .countByIdRestauranteIdAndEsAdministradorTrueAndActivoTrue(restauranteId) >= 2) {
                    throw new RuntimeException(
                            "Ya se ha alcanzado el límite de administradores para este restaurante (máximo 2).");
                }
            }
            // Si existe pero está inactiva, reactivarla
            existing.setActivo(true);
            existing.setEsAdministrador(esAdministrador);
            existing.setFechaAsociacion(Instant.now());
            UserRestaurante saved = userRestauranteRepository.save(existing);
            return userRestauranteMapper.toDTO(saved);
        }

        // Verificar si se va a crear como administrador
        if (esAdministrador) {
            if (userRestauranteRepository
                    .countByIdRestauranteIdAndEsAdministradorTrueAndActivoTrue(restauranteId) >= 2) {
                throw new RuntimeException(
                        "Ya se ha alcanzado el límite de administradores para este restaurante (máximo 2).");
            }
        }

        // Crear nueva asociación
        UserRestaurante userRestaurante = new UserRestaurante();
        userRestaurante.setId(id);
        userRestaurante.setUser(user);
        userRestaurante.setRestaurante(restaurante);
        userRestaurante.setEsAdministrador(esAdministrador);
        userRestaurante.setFechaAsociacion(Instant.now());
        userRestaurante.setActivo(true);

        UserRestaurante saved = userRestauranteRepository.save(userRestaurante);
        return userRestauranteMapper.toDTO(saved);
    }

    @Override
    public void removerUsuario(Long userId, Long restauranteId) {
        UserRestauranteId id = new UserRestauranteId(userId, restauranteId);
        UserRestaurante userRestaurante = userRestauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));

        userRestaurante.setActivo(false);
        userRestauranteRepository.save(userRestaurante);
    }

    @Override
    public UserRestauranteDTO cambiarPermisos(Long userId, Long restauranteId, Boolean esAdministrador) {
        UserRestauranteId id = new UserRestauranteId(userId, restauranteId);
        UserRestaurante userRestaurante = userRestauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));

        if (esAdministrador && !userRestaurante.getEsAdministrador()) {
            if (userRestauranteRepository
                    .countByIdRestauranteIdAndEsAdministradorTrueAndActivoTrue(restauranteId) >= 2) {
                throw new RuntimeException(
                        "Ya se ha alcanzado el límite de administradores para este restaurante (máximo 2).");
            }
        }

        userRestaurante.setEsAdministrador(esAdministrador);
        UserRestaurante saved = userRestauranteRepository.save(userRestaurante);
        return userRestauranteMapper.toDTO(saved);
    }

    @Override
    public List<UserRestauranteDTO> obtenerRestaurantesPorUsuario(Long userId) {
        return userRestauranteRepository.findByIdUserIdAndActivoTrue(userId)
                .stream()
                .map(userRestauranteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRestauranteDTO> obtenerUsuariosPorRestaurante(Long restauranteId) {
        return userRestauranteRepository.findByIdRestauranteIdAndActivoTrue(restauranteId)
                .stream()
                .map(userRestauranteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
