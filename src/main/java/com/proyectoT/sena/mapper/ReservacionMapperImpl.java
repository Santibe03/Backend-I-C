package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.dtos.CondicionDTO;
import com.proyectoT.sena.dtos.PersonDTO;
import com.proyectoT.sena.dtos.ReservacionDTO;
import com.proyectoT.sena.models.BarraMesa;
import com.proyectoT.sena.models.Condicion;
import com.proyectoT.sena.models.Person;
import com.proyectoT.sena.models.Reservacion;

@Component
public class ReservacionMapperImpl implements ReservacionMapper {

    @Override
    public ReservacionDTO toDto(Reservacion entity) {
        if (entity == null) return null;

        ReservacionDTO dto = new ReservacionDTO();

        dto.setId(entity.getId());
        dto.setAplicationDate(entity.getAplicationDate());
        dto.setReservationDate(entity.getReservationDate());
        dto.setAttendat(entity.getAttendat());

        dto.setPerson(toDtoPersonId(entity.getPerson()));
        dto.setCondicion(toDtoCondicionId(entity.getCondition()));
        dto.setBarraMesa(toDtoBarraMesaId(entity.getBarTable()));

        return dto;
    }

    @Override
    public Reservacion toEntity(ReservacionDTO dto) {
        if (dto == null) return null;

        Reservacion entity = new Reservacion();

        entity.setId(dto.getId());
        entity.setAplicationDate(dto.getAplicationDate());
        entity.setReservationDate(dto.getReservationDate());
        entity.setAttendat(dto.getAttendat());

        entity.setPerson(toEntityPerson(dto.getPerson()));
        entity.setCondition(toEntityCondicion(dto.getCondicion()));
        entity.setBarTable(toEntityBarraMesa(dto.getBarraMesa()));

        return entity;
    }

    @Override
    public PersonDTO toDtoPersonId(Person person) {
        if (person == null) return null;

        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setDocumentNumber(person.getDocumentNumber());
        dto.setFirstName(person.getFirstName());
        return dto;
    }

    @Override
    public CondicionDTO toDtoCondicionId(Condicion condicion) {
        if (condicion == null) return null;

        CondicionDTO dto = new CondicionDTO();
        dto.setId(condicion.getId());
        dto.setConditionName(condicion.getConditionName());
        return dto;
    }

    @Override
    public BarraMesaDTO toDtoBarraMesaId(BarraMesa barTable) {
        if (barTable == null) return null;

        BarraMesaDTO dto = new BarraMesaDTO();
        dto.setId(barTable.getId());
        dto.setAvalabily(barTable.getAvalabily());
        dto.setShare(barTable.getShare());
        return dto;
    }

    @Override
    public Person toEntityPerson(PersonDTO dto) {
        if (dto == null) return null;

        Person entity = new Person();
        entity.setId(dto.getId());
        return entity;
    }

    @Override
    public Condicion toEntityCondicion(CondicionDTO dto) {
        if (dto == null) return null;

        Condicion entity = new Condicion();
        entity.setId(dto.getId());
        return entity;
    }

    @Override
    public BarraMesa toEntityBarraMesa(BarraMesaDTO dto) {
        if (dto == null) return null;

        BarraMesa entity = new BarraMesa();
        entity.setId(dto.getId());
        return entity;
    }
}

