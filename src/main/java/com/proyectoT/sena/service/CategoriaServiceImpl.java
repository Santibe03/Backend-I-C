package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.CategoriaDTO;
import com.proyectoT.sena.mapper.CategoriaMapper;
import com.proyectoT.sena.models.Categoria;
import com.proyectoT.sena.repositoryes.CategoriaRepository;


import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public CategoriaDTO save(CategoriaDTO dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(categoria);
    }

    @Override
    public CategoriaDTO update(CategoriaDTO dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriaDTO> findOne(Long id) {
        return categoriaRepository.findById(id)
            .map(categoriaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}
