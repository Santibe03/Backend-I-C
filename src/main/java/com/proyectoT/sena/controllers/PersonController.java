package com.proyectoT.sena.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoT.sena.dtos.PersonDTO;
import com.proyectoT.sena.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // --------------------------
    // CREATE
    // --------------------------
    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        PersonDTO result = personService.save(personDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // --------------------------
    // UPDATE
    // --------------------------
    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(
            @PathVariable Long id,
            @RequestBody PersonDTO personDTO) {
        personDTO.setId(id);
        PersonDTO result = personService.update(personDTO);
        return ResponseEntity.ok(result);
    }

    // --------------------------
    // GET ALL
    // --------------------------
    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> list = personService.findAll();
        return ResponseEntity.ok(list);
    }

    // --------------------------
    // GET BY ID
    // --------------------------
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
        PersonDTO dto = personService.findOne(id);
        return ResponseEntity.ok(dto);
    }

    // --------------------------
    // DELETE
    // --------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


