package com.proyectoT.sena.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoT.sena.dtos.PersonDTO;
import com.proyectoT.sena.mapper.PersonMapper;
import com.proyectoT.sena.models.Person;
import com.proyectoT.sena.repositoryes.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    @Override
    public PersonDTO update(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PersonDTO findOne(Long id) {
        return personRepository.findById(id)
                .map(personMapper::toDto)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}


