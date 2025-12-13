package com.proyectoT.sena.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.PersonDTO;
import com.proyectoT.sena.dtos.RegisterRequestDTO;
import com.proyectoT.sena.dtos.UserDTO;
import com.proyectoT.sena.mapper.PersonMapper;
import com.proyectoT.sena.mapper.UserMapper;
import com.proyectoT.sena.models.Person;
import com.proyectoT.sena.models.User;
import com.proyectoT.sena.repositoryes.PersonRepository;
import com.proyectoT.sena.repositoryes.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional // IMPORTANTE: Para guardar cambios en DB automáticamente
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final UserMapper userMapper;
    private final PersonMapper personMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO save(UserDTO dto) {
        User entity = userMapper.toEntity(dto);
        // Encriptar password antes de guardar
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setActivated(true); // Activamos por defecto para probar
        User saved = userRepository.save(entity);
        return userMapper.toDTO(saved);
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        existing.setLogin(dto.getLogin());
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setActivated(dto.isActivated());
        existing.setLangKey(dto.getLangKey());
        existing.setImageUrl(dto.getImageUrl());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        User updated = userRepository.save(existing);
        return userMapper.toDTO(updated);
    }

    @Override
    public UserDTO findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return userMapper.toDTO(entity);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDTO registerUser(RegisterRequestDTO dto) {
        // 1. Crear y guardar el usuario
        UserDTO userDto = dto.getUser();
        User userEntity = userMapper.toEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setActivated(true); // O la lógica que prefieras
        User savedUser = userRepository.save(userEntity);

        // 2. Crear y guardar la persona, asociando el usuario
        PersonDTO personDto = dto.getPerson();
        Person personEntity = personMapper.toEntity(personDto);
        personEntity.setUser(savedUser); // Asociar el usuario guardado
        personRepository.save(personEntity);

        return userMapper.toDTO(savedUser);
    }

    // --- LÓGICA DE RECUPERACIÓN DE CONTRASEÑA ---

    @Override
    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmail(mail)
            .filter(User::isActivated)
            .map(user -> {
                user.setResetKey(UUID.randomUUID().toString()); // Generar Token
                user.setResetDate(Instant.now()); // Guardar fecha actual
                return user; // El @Transactional guarda esto en DB
            });
    }

    @Override
    public User completePasswordReset(String newPassword, String key) {
        return userRepository.findOneByResetKey(key)
            .filter(user -> user.getResetDate().isAfter(Instant.now().minus(24, ChronoUnit.HOURS))) // Validar 24h
            .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null); // Borrar token usado
                user.setResetDate(null);
                return user;
            })
            .orElseThrow(() -> new RuntimeException("Token inválido o expirado"));
    }
}



