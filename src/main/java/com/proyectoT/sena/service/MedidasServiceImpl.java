package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.MedidasDTO;
import com.proyectoT.sena.mapper.MedidasMapper;
import com.proyectoT.sena.models.Medidas;
import com.proyectoT.sena.repositoryes.MedidaRepository;


@Service
@Transactional
public class MedidasServiceImpl implements MedidasService {

    private final MedidaRepository repository;
    private final MedidasMapper mapper;

    public MedidasServiceImpl(MedidaRepository repository, MedidasMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MedidasDTO save(MedidasDTO dto) {
        Medidas entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public MedidasDTO update(MedidasDTO dto) {
        Medidas entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public Optional<MedidasDTO> findOne(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<MedidasDTO> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
