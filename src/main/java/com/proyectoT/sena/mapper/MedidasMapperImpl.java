package com.proyectoT.sena.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.MedidasDTO;
import com.proyectoT.sena.models.Medidas;

@Component
public class MedidasMapperImpl implements MedidasMapper {

    @Override
    public MedidasDTO toDto(Medidas entity) {
        if (entity == null) return null;

        MedidasDTO dto = new MedidasDTO();
        dto.setId(entity.getId());
        dto.setMedNam(entity.getMedNam());
        dto.setAbreviacion(entity.getAbreviacion());
        return dto;
    }

    @Override
    public Medidas toEntity(MedidasDTO dto) {
        if (dto == null) return null;

        Medidas entity = new Medidas();
        entity.setId(dto.getId());
        entity.setMedNam(dto.getMedNam());
        entity.setAbreviacion(dto.getAbreviacion());
        return entity;
    }

    @Override
    public List<MedidasDTO> toDtoList(List<Medidas> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Medidas> toEntityList(List<MedidasDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
