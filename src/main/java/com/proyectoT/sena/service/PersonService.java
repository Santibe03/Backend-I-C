package com.proyectoT.sena.service;

import java.util.List;
import com.proyectoT.sena.dtos.PersonDTO;

public interface PersonService {
    PersonDTO save(PersonDTO personDTO);
    PersonDTO update(PersonDTO personDTO);
    List<PersonDTO> findAll();
    PersonDTO findOne(Long id);
    void delete(Long id);
}

