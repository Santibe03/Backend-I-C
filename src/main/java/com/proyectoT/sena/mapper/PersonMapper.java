package com.proyectoT.sena.mapper;

import com.proyectoT.sena.dtos.PersonDTO;
import com.proyectoT.sena.models.Person;

public interface PersonMapper {

    PersonDTO toDto(Person entity);

    Person toEntity(PersonDTO dto);
}


