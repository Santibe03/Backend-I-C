package com.proyectoT.sena.service;

import com.proyectoT.sena.dtos.InsumoDTO;
import com.proyectoT.sena.mapper.InsumoMapper;
import com.proyectoT.sena.models.Insumo;
import com.proyectoT.sena.repositoryes.InsumoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsumoServiceImpl implements InsumoService {

    private final InsumoRepository insumoRepository;
    private final InsumoMapper insumoMapper;

    @Override
    public List<InsumoDTO> findAll() {
        return insumoRepository.findAll()
                .stream()
                .map(insumoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InsumoDTO findById(Long id) {
        return insumoRepository.findById(id)
                .map(insumoMapper::toDTO)
                .orElse(null);
    }

    @Override
    public InsumoDTO save(InsumoDTO dto) {
        Insumo entity = insumoMapper.toEntity(dto);
        Insumo saved = insumoRepository.save(entity);
        return insumoMapper.toDTO(saved);
    }

    @Override
    public InsumoDTO update(Long id, InsumoDTO dto) {
        return insumoRepository.findById(id)
                .map(existing -> {
                    existing.setInputName(dto.getNombre());
                    existing.setBrand(dto.getMarca());
                    existing.setAmount(dto.getCantidad());

                    if (dto.getCategoriaId() != null) {
                        existing.getCategory().setId(dto.getCategoriaId());
                    }
                    if (dto.getMedidaId() != null) {
                        existing.getMeasure().setId(dto.getMedidaId());
                    }

                    Insumo updated = insumoRepository.save(existing);
                    return insumoMapper.toDTO(updated);
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        insumoRepository.deleteById(id);
    }
}
