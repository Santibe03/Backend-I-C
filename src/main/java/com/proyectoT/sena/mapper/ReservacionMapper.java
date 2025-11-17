package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.dtos.CondicionDTO;
import com.proyectoT.sena.dtos.PersonDTO;
import com.proyectoT.sena.dtos.ReservacionDTO;
import com.proyectoT.sena.models.BarraMesa;
import com.proyectoT.sena.models.Condicion;
import com.proyectoT.sena.models.Person;
import com.proyectoT.sena.models.Reservacion;

public interface ReservacionMapper {

    ReservacionDTO toDto(Reservacion entity);

    Reservacion toEntity(ReservacionDTO dto);

    PersonDTO toDtoPersonId(Person person);
    CondicionDTO toDtoCondicionId(Condicion condicion);
    BarraMesaDTO toDtoBarraMesaId(BarraMesa barTable);

    Person toEntityPerson(PersonDTO personDto);
    Condicion toEntityCondicion(CondicionDTO condicionDto);
    BarraMesa toEntityBarraMesa(BarraMesaDTO barTableDto);
}

