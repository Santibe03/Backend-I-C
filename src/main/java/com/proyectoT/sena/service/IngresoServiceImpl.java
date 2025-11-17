package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyectoT.sena.dtos.IngresoDTO;
import com.proyectoT.sena.mapper.IngresoMapper;
import com.proyectoT.sena.models.Ingresos;
import com.proyectoT.sena.repositoryes.IngresosRepository;


@Service
public class IngresoServiceImpl implements IngresoService {

    private final IngresosRepository ingresoRepository;
    private final IngresoMapper ingresoMapper;

    public IngresoServiceImpl(IngresosRepository ingresoRepository, IngresoMapper ingresoMapper) {
        this.ingresoRepository = ingresoRepository;
        this.ingresoMapper = ingresoMapper;
    }

    @Override
    public IngresoDTO save(IngresoDTO dto) {
        Ingresos ingreso = ingresoMapper.toEntity(dto);
        ingreso = ingresoRepository.save(ingreso);
        return ingresoMapper.toDto(ingreso);
    }

    @Override
    public IngresoDTO update(IngresoDTO dto) {
        Ingresos ingreso = ingresoMapper.toEntity(dto);
        ingreso = ingresoRepository.save(ingreso);
        return ingresoMapper.toDto(ingreso);
    }

    @Override
    public Optional<IngresoDTO> findOne(Long id) {
        return ingresoRepository.findById(id)
                .map(ingresoMapper::toDto);
    }

    @Override
    public List<IngresoDTO> findAll() {
        return ingresoRepository.findAll()
                .stream()
                .map(ingresoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        ingresoRepository.deleteById(id);
    }
}
