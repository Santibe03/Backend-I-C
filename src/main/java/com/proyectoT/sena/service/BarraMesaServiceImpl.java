package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.mapper.BarraMesaMapper;
import com.proyectoT.sena.models.BarraMesa;
import com.proyectoT.sena.repositoryes.BarraMesaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BarraMesaServiceImpl implements BarraMesaService {

    private final BarraMesaRepository repository;
    private final BarraMesaMapper mapper;

    @Override
    public BarraMesaDTO save(BarraMesaDTO dto) {
        BarraMesa mesa = mapper.toEntity(dto);
        mesa = repository.save(mesa);
        return mapper.toDto(mesa);
    }

    @Override
    public BarraMesaDTO update(BarraMesaDTO dto) {
        BarraMesa mesa = mapper.toEntity(dto);
        mesa = repository.save(mesa);
        return mapper.toDto(mesa);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BarraMesaDTO> findOne(Long id) {
        return repository.findById(id)
            .map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BarraMesaDTO> findAll() {
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
