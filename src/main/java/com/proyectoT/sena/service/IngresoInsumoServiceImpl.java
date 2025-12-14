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

import com.proyectoT.sena.models.Insumo;
import com.proyectoT.sena.repositoryes.InsumoRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class IngresoInsumoServiceImpl implements IngresoInsumoService {

    private final IngInsuRepository repository;
    private final InsumoRepository insumoRepository; // Inject Insumo Repo
    private final IngresoInsumoMapper mapper;

    @Override
    public IngresoInsumoDTO save(IngresoInsumoDTO dto) {
        IngresoInsumo entity = mapper.toEntity(dto);

        // 1. Fetch Insumo REAL
        Insumo insumo = insumoRepository.findById(dto.getInputId())
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));

        // 2. Add Stock
        insumo.setAmount(insumo.getAmount() + dto.getAmount());
        insumoRepository.save(insumo); // Update Stock

        // 3. Link real insumo to entity
        entity.setInput(insumo);
        entity.setMeasure(insumo.getMeasure()); // Use Insumo's measure or DTO's? Should match.

        // 4. Save Income History
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
