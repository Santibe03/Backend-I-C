package com.proyectoT.sena.models;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name="id_person")
    private long id;

    @Column(nullable = false, unique = true)
    private Long documentNumber;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50)
    private String secondName;

     @Column(length = 50, nullable = false)
    private String firtLastName;

     @Column(length = 50)
    private String secondLastName;

     @Column(nullable = false, unique = true)
    private Long phoneNumber;

    
    private LocalDate bornDate;


}
