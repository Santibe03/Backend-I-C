package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;
import com.proyectoT.sena.dtos.ReservacionDTO;
import com.proyectoT.sena.models.Reservacion;
import com.proyectoT.sena.models.Person;
import com.proyectoT.sena.models.BarraMesa;

@Component
public class ReservacionMapper {

    public ReservacionDTO toDto(Reservacion entity) {
        if (entity == null) {
            return null;
        }
        ReservacionDTO dto = new ReservacionDTO();
        dto.setId(entity.getId());
        dto.setAplicationDate(entity.getAplicationDate());
        dto.setReservationDate(entity.getReservationDate());
        dto.setAttendat(entity.getAttendat());
        dto.setCondition(entity.getCondition());

        if (entity.getPerson() != null) {
            dto.setPersonId(entity.getPerson().getId());
        }
        if (entity.getBarTable() != null) {
            dto.setBarTableId(entity.getBarTable().getId());
        }

        return dto;
    }

    public Reservacion toEntity(ReservacionDTO dto) {
        if (dto == null) {
            return null;
        }
        Reservacion entity = new Reservacion();
        entity.setId(dto.getId());
        entity.setAplicationDate(dto.getAplicationDate());
        entity.setReservationDate(dto.getReservationDate());
        entity.setAttendat(dto.getAttendat());
        entity.setCondition(dto.getCondition());

        // Set placeholders for relationships if IDs are present
        // The Service is responsible for fetching full entities if needed,
        // or we can set objects with just IDs here.
        if (dto.getPersonId() != null) {
            Person p = new Person();
            p.setId(dto.getPersonId());
            entity.setPerson(p);
        }

        if (dto.getBarTableId() != null) {
            BarraMesa b = new BarraMesa();
            b.setId(dto.getBarTableId());
            entity.setBarTable(b);
        }

        return entity;
    }
}
