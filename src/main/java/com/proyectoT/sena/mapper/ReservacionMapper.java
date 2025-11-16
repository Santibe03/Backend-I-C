package com.proyectoT.sena.mapper;

import org.springframework.web.bind.annotation.Mapping;

import com.proyectoT.sena.dtos.BarraMesaDTO;
import com.proyectoT.sena.dtos.CondicionDTO;
import com.proyectoT.sena.dtos.PersonDTO;
import com.proyectoT.sena.dtos.ReservacionDTO;
import com.proyectoT.sena.models.Person;
import com.proyectoT.sena.models.Condicion;
import com.proyectoT.sena.models.BarraMesa;
import com.proyectoT.sena.models.Reservacion;

public interface ReservacionMapper {
    ReservacionDTO toDto(Reservacion s);
    PersonDTO toDtoPersonId(Person person);
    CondicionDTO toDtoCondicionId(Condicion condition);
    BarraMesaDTO toDtoBarraMesaId(BarraMesa barTable);
}
