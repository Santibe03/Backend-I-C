package com.proyectoT.sena.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_person")
    private Long id;

    

    @NotNull
    @Column(name = "document_number", nullable = false, unique = true)
    private Long documentNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(name = "second_name", length = 50)
    private String secondName;

    @NotNull
    @Size(max = 50)
    @Column(name = "firt_last_name", length = 50, nullable = false)
    private String firtLastName; 

    @Size(max = 50)
    @Column(name = "second_last_name", length = 50)
    private String secondLastName;

    @NotNull
    @Column(name = "phone_number", nullable = false, unique = true)
    private Long phoneNumber;

    @Column(name = "born_date")
    private LocalDate bornDate;

  
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    @JoinColumn(name = "user_id", unique = true) 
    private User user; 


    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "document_type_id")
    private TipoDocumento documentType;


    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    @JsonIgnore 
    private Set<Ingresos> incomes = new HashSet<>(); 

    
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Factura> bills = new HashSet<>(); 

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Reservacion> reservations = new HashSet<>(); 
}
