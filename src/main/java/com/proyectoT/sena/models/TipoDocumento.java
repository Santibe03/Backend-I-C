package com.proyectoT.sena.models;

import java.io.Serializable;
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
    @Enumerated(EnumType.STRING) 
    @Column(name = "state_document_type", nullable = false)
    private Condicion stateDocumentType; 
    
    
    @OneToMany(mappedBy = "documentType", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Person> people = new HashSet<>(); 
}

