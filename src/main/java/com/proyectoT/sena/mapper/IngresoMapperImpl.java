package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.IngresoDTO;
import com.proyectoT.sena.models.Ingresos;

@Component
public class IngresoMapperImpl implements IngresoMapper {

    private final PersonMapper personMapper;

    public IngresoMapperImpl(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public Ingresos toEntity(IngresoDTO dto) {
        if (dto == null) {
            return null;
        }

        Ingresos ingreso = new Ingresos();

        ingreso.setId(dto.getId());
        ingreso.setFecha(dto.getFecha());
        ingreso.setDetalles(dto.getDetalles());
        ingreso.setPerson(personMapper.toEntity(dto.getPerson()));

        return ingreso;
    }

    @Override
    public IngresoDTO toDto(Ingresos entity) {
        if (entity == null) {
            return null;
        }

        IngresoDTO dto = new IngresoDTO();

        dto.setId(entity.getId());
        dto.setFecha(entity.getFecha());
        dto.setDetalles(entity.getDetalles());
        dto.setPerson(personMapper.toDto(entity.getPerson()));

        return dto;
    }
}
