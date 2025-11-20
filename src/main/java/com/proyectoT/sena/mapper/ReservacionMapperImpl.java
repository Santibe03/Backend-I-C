package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.ReservacionDTO;
import com.proyectoT.sena.models.Reservacion;

@Component
public class ReservacionMapperImpl implements ReservacionMapper {

    private final PersonMapper personMapper;
    private final CondicionMapper condicionMapper;
    private final BarraMesaMapper barraMesaMapper;

    public ReservacionMapperImpl(
            PersonMapper personMapper,
            CondicionMapper condicionMapper,
            BarraMesaMapper barraMesaMapper
    ) {
        this.personMapper = personMapper;
        this.condicionMapper = condicionMapper;
        this.barraMesaMapper = barraMesaMapper;
    }

    // =========================================
    //              TO DTO
    // =========================================
    @Override
    public ReservacionDTO toDto(Reservacion entity) {
        if (entity == null) return null;

        ReservacionDTO dto = new ReservacionDTO();

        dto.setId(entity.getId());
        dto.setAplicationDate(entity.getAplicationDate());
        dto.setReservationDate(entity.getReservationDate());
        dto.setAttendat(entity.getAttendat());

        // Relaciones -----------------------------
        if (entity.getPerson() != null) {
            dto.setPerson(personMapper.toDto(entity.getPerson()));
        }

        if (entity.getCondition() != null) {
            dto.setCondicion(condicionMapper.toDto(entity.getCondition()));
        }

        if (entity.getBarTable() != null) {
            dto.setBarraMesa(barraMesaMapper.toDto(entity.getBarTable()));
        }

        return dto;
    }

    // =========================================
    //              TO ENTITY
    // =========================================
    @Override
    public Reservacion toEntity(ReservacionDTO dto) {
        if (dto == null) return null;

        Reservacion entity = new Reservacion();

        entity.setId(dto.getId());
        entity.setAplicationDate(dto.getAplicationDate());
        entity.setReservationDate(dto.getReservationDate());
        entity.setAttendat(dto.getAttendat());

        // Relaciones -----------------------------
        if (dto.getPerson() != null) {
            entity.setPerson(personMapper.toEntity(dto.getPerson()));
        }

        if (dto.getCondicion() != null) {
            entity.setCondition(condicionMapper.toEntity(dto.getCondicion()));
        }

        if (dto.getBarraMesa() != null) {
            entity.setBarTable(barraMesaMapper.toEntity(dto.getBarraMesa()));
        }

        return entity;
    }
}
