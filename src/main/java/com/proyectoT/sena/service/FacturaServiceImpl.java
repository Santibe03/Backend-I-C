package com.proyectoT.sena.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.FacturaDTO;
import com.proyectoT.sena.mapper.FacturaMapper;
import com.proyectoT.sena.models.Factura;
import com.proyectoT.sena.models.Person;
import com.proyectoT.sena.repositoryes.FacturaRepository;
import com.proyectoT.sena.repositoryes.PersonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final PersonRepository personRepository;
    private final FacturaMapper facturaMapper;

    // ---------------------------------------------------------
    // CREATE
    // ---------------------------------------------------------
    @Override
    public FacturaDTO create(FacturaDTO dto) {

        // validar persona
        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        // convertir a entidad
        Factura entity = facturaMapper.toEntity(dto);

        // asignar relación
        entity.setPerson(person);

        facturaRepository.save(entity);

        return facturaMapper.toDTO(entity);
    }

    // ---------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------
    @Override
    public FacturaDTO update(FacturaDTO dto) {

        Factura existing = facturaRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        // actualizar campos simples
        facturaMapper.updateEntityFromDTO(dto, existing);

        // actualizar relación persona si viene en dto
        if (dto.getPersonId() != null) {
            Person person = personRepository.findById(dto.getPersonId())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

            existing.setPerson(person);
        }

        facturaRepository.save(existing);

        return facturaMapper.toDTO(existing);
    }

    // ---------------------------------------------------------
    // FIND ALL
    // ---------------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public List<FacturaDTO> findAll() {
        return facturaRepository.findAll()
                .stream()
                .map(facturaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // FIND BY ID
    // ---------------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public FacturaDTO findById(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        return facturaMapper.toDTO(factura);
    }

    // ---------------------------------------------------------
    // DELETE
    // ---------------------------------------------------------
    @Override
    public void delete(Long id) {
        if (!facturaRepository.existsById(id)) {
            throw new RuntimeException("Factura no encontrada");
        }
        facturaRepository.deleteById(id);
    }
}
