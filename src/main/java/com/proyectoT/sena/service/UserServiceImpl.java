package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.UserDTO;
import com.proyectoT.sena.mapper.UserMapperImpl;
import com.proyectoT.sena.models.User;
import com.proyectoT.sena.repositoryes.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapperImpl userMapper;

    @Override
    public UserDTO save(UserDTO dto) {
        User entity = userMapper.toEntity(dto);
        entity = userRepository.save(entity);
        return userMapper.toDto(entity);
    }

    @Override
    public UserDTO update(UserDTO dto) {
        User entity = userMapper.toEntity(dto);
        entity = userRepository.save(entity);
        return userMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findOne(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
