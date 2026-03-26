package com.proyectoT.sena.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.mapper.BarraMesaMapper;
import com.proyectoT.sena.models.BarraMesa;
import com.proyectoT.sena.repositoryes.BarraMesaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BarraMesaServiceImpl implements BarraMesaService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BarraMesaServiceImpl.class);

    private final BarraMesaRepository repository;
    private final BarraMesaMapper mapper;
    private final com.proyectoT.sena.repositoryes.RestauranteRepository restauranteRepository;

    @Override
    public BarraMesaDTO save(BarraMesaDTO dto) {
        log.info("=== GUARDANDO MESA ===");
        log.info("DTO recibido: availability={}, share={}, number={}, restauranteId={}",
                dto.getAvailability(), dto.getShare(), dto.getNumber(), dto.getRestauranteId());

        BarraMesa mesa = mapper.toEntity(dto);
        log.info("Mesa mapeada: availability={}, share={}", mesa.getAvailability(), mesa.getShare());

        // Fetch the actual Restaurante entity from database
        if (dto.getRestauranteId() != null) {
            log.info("Buscando restaurante con ID: {}", dto.getRestauranteId());
            com.proyectoT.sena.models.Restaurante restaurante = restauranteRepository.findById(dto.getRestauranteId())
                    .orElseThrow(
                            () -> new RuntimeException("Restaurante no encontrado con ID: " + dto.getRestauranteId()));
            log.info("Restaurante encontrado: {}", restaurante.getNombre());
            mesa.setRestaurante(restaurante);
        } else {
            log.error("ERROR: restauranteId es NULL!");
        }

        log.info("Guardando en BD...");
        mesa = repository.save(mesa);
        log.info("Mesa guardada exitosamente con ID: {}", mesa.getId());
        return mapper.toDto(mesa);
    }

    @Override
    public BarraMesaDTO update(BarraMesaDTO dto) {
        log.info("=== ACTUALIZANDO MESA ID: {} ===", dto.getId());

        // Fetch the existing mesa to preserve the restaurante relationship
        BarraMesa existingMesa = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada con ID: " + dto.getId()));

        log.info("Mesa existente encontrada, restaurante: {}",
                existingMesa.getRestaurante() != null ? existingMesa.getRestaurante().getId() : "NULL");

        // Update only the fields that should change
        existingMesa.setAvailability(dto.getAvailability());
        existingMesa.setShare(dto.getShare());
        existingMesa.setNumber(dto.getNumber());
        // DO NOT change the restaurante - keep the existing one

        existingMesa = repository.save(existingMesa);
        log.info("Mesa actualizada exitosamente");
        return mapper.toDto(existingMesa);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BarraMesaDTO> findOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BarraMesaDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BarraMesaDTO> findAllByRestaurante(Long restauranteId) {
        return repository.findAllByRestauranteId(restauranteId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
