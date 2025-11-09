package com.proyectoT.sena.models;

import java.io.Serializable;
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
@Table(name = "income") // Usamos el nombre de tabla de la referencia
public class Ingresos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingresos")
    private Long id;

    @Column(name = "date") 
    private LocalDate fecha; 

    @Size(max = 255)
    @Column(name = "details", length = 255)
    private String detalles; 


    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "person_id", nullable = false)
    private Person person; 


    @OneToMany(mappedBy = "income", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<IngresoInsumo> incomeInputs = new HashSet<>(); 
}
