package com.proyectoT.sena.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;
    private Long documentNumber;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private Long phoneNumber;
    private LocalDate bornDate;

    // Relaciones representadas por ID
    private Long userId;
    private Long documentTypeId;

    // Campos opcionales descriptivos
    private String userLogin;
    private String documentTypeName;
}