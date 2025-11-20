package com.proyectoT.sena.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.OrdenDTO;
import com.proyectoT.sena.mapper.OrdenMapper;
import com.proyectoT.sena.models.Orden;
import com.proyectoT.sena.models.BarraMesa;
import com.proyectoT.sena.models.Condicion;
import com.proyectoT.sena.repositoryes.OrdenRepository;
import com.proyectoT.sena.repositoryes.MesaRepository;
import com.proyectoT.sena.repositoryes.CondicionRepository;
import com.proyectoT.sena.service.OrdenService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final MesaRepository mesaRepository;
    private final CondicionRepository condicionRepository;
    private final OrdenMapper ordenMapper;

    @Override
    public OrdenDTO create(OrdenDTO dto) {

        // validar y obtener relaciones
        BarraMesa barra = mesaRepository.findById(dto.getBarraMesaId())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        Condicion condicion = condicionRepository.findById(dto.getCondicionId())
                .orElseThrow(() -> new RuntimeException("Condición no encontrada"));

        Orden entity = ordenMapper.toEntity(dto);

        entity.setBarTable(barra);
        entity.setCondition(condicion);

        entity = ordenRepository.save(entity);

        return ordenMapper.toDto(entity);
    }

    @Override
    public OrdenDTO update(OrdenDTO dto) {
        Orden existing = ordenRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        ordenMapper.updateEntityFromDTO(dto, existing);

        if (dto.getBarraMesaId() != null) {
            BarraMesa barra = mesaRepository.findById(dto.getBarraMesaId())  // <-- CORREGIDO
                    .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
            existing.setBarTable(barra);
        }

        if (dto.getCondicionId() != null) {
            Condicion condicion = condicionRepository.findById(dto.getCondicionId())
                    .orElseThrow(() -> new RuntimeException("Condición no encontrada"));
            existing.setCondition(condicion);
        }

        existing = ordenRepository.save(existing);

        return ordenMapper.toDto(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenDTO> findAll() {
        return ordenRepository.findAll()
                .stream()
                .map(ordenMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenDTO findById(Long id) {
        Orden entity = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        return ordenMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        if (!ordenRepository.existsById(id)) {
            throw new RuntimeException("Orden no encontrada");
        }
        ordenRepository.deleteById(id);
    }
}
