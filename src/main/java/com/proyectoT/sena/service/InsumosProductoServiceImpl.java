package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.InsumosProductoDTO;
import com.proyectoT.sena.mapper.InsumosProductoMapper;
import com.proyectoT.sena.models.InsumosProducto;
import com.proyectoT.sena.repositoryes.InsumosProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InsumosProductoServiceImpl implements InsumosProductoService {

    private final InsumosProductRepository repository;
    private final InsumosProductoMapper mapper;

    @Override
    public InsumosProductoDTO save(InsumosProductoDTO dto) {
        InsumosProducto entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InsumosProductoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InsumosProductoDTO> findOne(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
