package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.PersonDTO;
import com.proyectoT.sena.models.Person;

@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonDTO toDto(Person entity) {
        if (entity == null) {
            return null;
        }

        PersonDTO dto = new PersonDTO();

        dto.setId(entity.getId());
        dto.setDocumentNumber(entity.getDocumentNumber());
        dto.setFirstName(entity.getFirstName());
        dto.setSecondName(entity.getSecondName());
        dto.setFirstLastName(entity.getFirstLastName());
        dto.setSecondLastName(entity.getSecondLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setBornDate(entity.getBornDate());

        // Relaciones (solo IDs)
        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }

        if (entity.getDocumentType() != null) {
            dto.setDocumentTypeId(entity.getDocumentType().getId());
        }

        return dto;
    }

    @Override
    public Person toEntity(PersonDTO dto) {
        if (dto == null) {
            return null;
        }

        Person entity = new Person();

        entity.setId(dto.getId());
        entity.setDocumentNumber(dto.getDocumentNumber());
        entity.setFirstName(dto.getFirstName());
        entity.setSecondName(dto.getSecondName());
        entity.setFirstLastName(dto.getFirstLastName());
        entity.setSecondLastName(dto.getSecondLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setBornDate(dto.getBornDate());

        // OJO: relaciones no se instancian aquí
        // Se deben asignar en el Service usando repositorios

        return entity;
    }
}


