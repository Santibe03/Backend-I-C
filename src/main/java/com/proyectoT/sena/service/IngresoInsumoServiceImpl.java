package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.IngresoInsumoDTO;
import com.proyectoT.sena.mapper.IngresoInsumoMapper;
import com.proyectoT.sena.models.IngresoInsumo;
import com.proyectoT.sena.repositoryes.IngInsuRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IngresoInsumoServiceImpl implements IngresoInsumoService {

    private final IngInsuRepository repository;
    private final IngresoInsumoMapper mapper;

    @Override
    public IngresoInsumoDTO save(IngresoInsumoDTO dto) {
        IngresoInsumo entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IngresoInsumoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IngresoInsumoDTO> findOne(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
