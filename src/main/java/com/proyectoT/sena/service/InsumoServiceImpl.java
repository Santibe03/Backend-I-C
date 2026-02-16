package com.proyectoT.sena.service;

import com.proyectoT.sena.dtos.InsumoDTO;
import com.proyectoT.sena.mapper.InsumoMapper;
import com.proyectoT.sena.models.Insumo;
import com.proyectoT.sena.models.Restaurante;
import com.proyectoT.sena.repositoryes.InsumoRepository;
import com.proyectoT.sena.repositoryes.RestauranteRepository;
import com.proyectoT.sena.service.InsumoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsumoServiceImpl implements InsumoService {

    private final InsumoRepository insumoRepository;
    private final RestauranteRepository restauranteRepository;
    private final InsumoMapper insumoMapper;

    @Override
    public InsumoDTO save(InsumoDTO dto) {
        Insumo entity = insumoMapper.toEntity(dto);

        if (dto.getRestauranteId() != null) {
            Restaurante restaurante = restauranteRepository.findById(dto.getRestauranteId())
                    .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
            entity.setRestaurante(restaurante);
        }

        entity = insumoRepository.save(entity);
        return insumoMapper.toDTO(entity);
    }

    @Override
    public InsumoDTO update(InsumoDTO dto) {
        Insumo entity = insumoMapper.toEntity(dto);

        // Preserve or update restaurant
        if (dto.getRestauranteId() != null) {
            Restaurante restaurante = restauranteRepository.findById(dto.getRestauranteId())
                    .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
            entity.setRestaurante(restaurante);
        } else {
            // Try to preserve existing if not provided
            Optional<Insumo> existing = insumoRepository.findById(dto.getId());
            existing.ifPresent(e -> entity.setRestaurante(e.getRestaurante()));
        }

        Insumo saved = insumoRepository.save(entity);
        return insumoMapper.toDTO(saved);
    }

    @Override
    public Optional<InsumoDTO> findOne(Long id) {
        return insumoRepository.findById(id)
                .map(insumoMapper::toDTO);
    }

    @Override
    public List<InsumoDTO> findAll(Long restauranteId) {
        return insumoRepository.findByRestauranteId(restauranteId)
                .stream()
                .map(insumoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        insumoRepository.deleteById(id);
    }

    @Override
    public void deductStock(Long id, Double amount) {
        Insumo insumo = insumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));

        if (insumo.getAmount() < amount) {
            throw new RuntimeException("Stock insuficiente para el insumo: " + insumo.getInputName());
        }

        insumo.setAmount(insumo.getAmount() - amount);
        insumoRepository.save(insumo);
    }
}
