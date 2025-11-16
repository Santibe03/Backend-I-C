package com.proyectoT.sena.models;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "document_type") 
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "initials", length = 10, nullable = false, unique = true)
    private String initials;

    @NotNull
    @Size(max = 100)
    @Column(name = "document_name", length = 100, nullable = false, unique = true)
    private String documentName;

    @NotNull
    @Column(name = "state_document_type", nullable = false)
    private String stateDocumentType; 
    
    
    @OneToMany(mappedBy = "documentType")
    private Set<Person> people;
}

