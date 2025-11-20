package com.proyectoT.sena.mapper;

import org.springframework.stereotype.Component;

import com.proyectoT.sena.dtos.PersonDTO;
import com.proyectoT.sena.models.Person;
import com.proyectoT.sena.models.TipoDocumento;
import com.proyectoT.sena.models.User;

@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonDTO toDto(Person entity) {
        if (entity == null) return null;

        PersonDTO dto = new PersonDTO();
        dto.setId(entity.getId());
        dto.setDocumentNumber(entity.getDocumentNumber());
        dto.setFirstName(entity.getFirstName());
        dto.setSecondName(entity.getSecondName());
        dto.setFirstLastName(entity.getFirstLastName());
        dto.setSecondLastName(entity.getSecondLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setBornDate(entity.getBornDate());

        if (entity.getUser() != null)
            dto.setUserId(entity.getUser().getId());

        if (entity.getDocumentType() != null)
            dto.setDocumentTypeId(entity.getDocumentType().getId());

        return dto;
    }

    @Override
    public Person toEntity(PersonDTO dto) {
        if (dto == null) return null;

        Person p = new Person();

        p.setId(dto.getId());
        p.setDocumentNumber(dto.getDocumentNumber());
        p.setFirstName(dto.getFirstName());
        p.setSecondName(dto.getSecondName());
        p.setFirstLastName(dto.getFirstLastName());
        p.setSecondLastName(dto.getSecondLastName());
        p.setPhoneNumber(dto.getPhoneNumber());
        p.setBornDate(dto.getBornDate());

        if (dto.getUserId() != null) {
            User u = new User();
            u.setId(dto.getUserId());
            p.setUser(u);
        }

        if (dto.getDocumentTypeId() != null) {
            TipoDocumento td = new TipoDocumento();
            td.setId(dto.getDocumentTypeId());
            p.setDocumentType(td);
        }

        return p;
    }
}


