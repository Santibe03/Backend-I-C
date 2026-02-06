package com.proyectoT.sena.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.RestauranteDTO;
import com.proyectoT.sena.mapper.RestauranteMapper;
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
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final UserRepository userRepository;
    private final UserRestauranteRepository userRestauranteRepository;
    private final RestauranteMapper restauranteMapper;

    @Override
    public RestauranteDTO crear(RestauranteDTO dto, Long usuarioCreadorId) {
        // Buscar el usuario creador
        User usuarioCreador = userRepository.findById(usuarioCreadorId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear el restaurante
        Restaurante restaurante = restauranteMapper.toEntity(dto);
        restaurante.setUsuarioCreador(usuarioCreador);
        restaurante.setFechaCreacion(Instant.now());
        restaurante.setActivo(true);

        Restaurante savedRestaurante = restauranteRepository.save(restaurante);

        // Crear la asociación UserRestaurante con permisos de administrador
        UserRestauranteId userRestId = new UserRestauranteId(usuarioCreadorId, savedRestaurante.getId());
        UserRestaurante userRestaurante = new UserRestaurante();
        userRestaurante.setId(userRestId);
        userRestaurante.setUser(usuarioCreador);
        userRestaurante.setRestaurante(savedRestaurante);
        userRestaurante.setEsAdministrador(true);
        userRestaurante.setFechaAsociacion(Instant.now());
        userRestaurante.setActivo(true);

        userRestauranteRepository.save(userRestaurante);

        return restauranteMapper.toDTO(savedRestaurante);
    }

    @Override
    public RestauranteDTO actualizar(Long id, RestauranteDTO dto) {
        Restaurante restaurante = restauranteRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

        restaurante.setNombre(dto.getNombre());
        restaurante.setDireccion(dto.getDireccion());
        restaurante.setContacto(dto.getContacto());

        Restaurante updated = restauranteRepository.save(restaurante);
        return restauranteMapper.toDTO(updated);
    }

    @Override
    public RestauranteDTO obtenerPorId(Long id) {
        Restaurante restaurante = restauranteRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        return restauranteMapper.toDTO(restaurante);
    }

    @Override
    public List<RestauranteDTO> listar() {
        return restauranteRepository.findByActivoTrue()
                .stream()
                .map(restauranteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestauranteDTO> buscarPorNombre(String nombre) {
        return restauranteRepository.findByNombreContainingIgnoreCaseAndActivoTrue(nombre)
                .stream()
                .map(restauranteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestauranteDTO> listarPorUsuario(Long userId) {
        List<UserRestaurante> userRestaurantes = userRestauranteRepository.findByIdUserIdAndActivoTrue(userId);

        return userRestaurantes.stream()
                .map(ur -> restauranteMapper.toDTO(ur.getRestaurante()))
                .collect(Collectors.toList());
    }

    @Override
    public void desactivar(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        restaurante.setActivo(false);
        restauranteRepository.save(restaurante);
    }
}
