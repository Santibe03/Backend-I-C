package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyectoT.sena.models.TipoDocumento;
import com.proyectoT.sena.repositoryes.TipDocRepository;
import com.proyectoT.sena.service.TipoDocumentoService;
import com.proyectoT.sena.mapper.TipoDocumentoMapper;
import com.proyectoT.sena.dtos.TipoDocumentoDTO;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final TipDocRepository repository;
    private final TipoDocumentoMapper mapper;

    public TipoDocumentoServiceImpl(TipDocRepository repository, TipoDocumentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TipoDocumentoDTO save(TipoDocumentoDTO dto) {
        TipoDocumento entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public TipoDocumentoDTO update(TipoDocumentoDTO dto) {
        TipoDocumento entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<TipoDocumentoDTO> findOne(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<TipoDocumentoDTO> findAll() {
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

