package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyectoT.sena.repositoryes.CondicionRepository;
import com.proyectoT.sena.mapper.CondicionMapper;
import com.proyectoT.sena.models.Condicion;
import com.proyectoT.sena.dtos.CondicionDTO;

@Service
public class CondicionServiceImpl implements CondicionService {

    private final CondicionRepository repository;
    private final CondicionMapper mapper;

    public CondicionServiceImpl(CondicionRepository repository, CondicionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CondicionDTO save(CondicionDTO dto) {
        Condicion entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public CondicionDTO update(CondicionDTO dto) {
        Condicion entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<CondicionDTO> findOne(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<CondicionDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
