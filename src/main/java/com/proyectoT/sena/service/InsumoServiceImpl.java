package com.proyectoT.sena.service;

import com.proyectoT.sena.dtos.InsumoDTO;
import com.proyectoT.sena.mapper.InsumoMapper;
import com.proyectoT.sena.models.Insumo;
import com.proyectoT.sena.repositoryes.InsumoRepository;
import com.proyectoT.sena.service.InsumoService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InsumoServiceImpl implements InsumoService {

    private final InsumoRepository insumoRepository;
    private final InsumoMapper insumoMapper;

    public InsumoServiceImpl(InsumoRepository insumoRepository, InsumoMapper insumoMapper) {
        this.insumoRepository = insumoRepository;
        this.insumoMapper = insumoMapper;
    }

    @Override
    public InsumoDTO save(InsumoDTO dto) {
        Insumo entity = insumoMapper.toEntity(dto);
        entity = insumoRepository.save(entity);
        return insumoMapper.toDTO(entity);
    }

    @Override
    public InsumoDTO update(InsumoDTO dto) {
        Insumo entity = insumoMapper.toEntity(dto);
        entity = insumoRepository.save(entity);
        return insumoMapper.toDTO(entity);
    }

    @Override
    public Optional<InsumoDTO> findOne(Long id) {
        return insumoRepository.findById(id)
                .map(insumoMapper::toDTO);
    }

    @Override
    public List<InsumoDTO> findAll() {
        return insumoRepository.findAll()
                .stream()
                .map(insumoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        insumoRepository.deleteById(id);
    }
}

