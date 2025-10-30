package com.proyectoT.sena.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bill") 
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long id;

    @Column(name = "total")
    private Integer total;

    @Column(name = "date")
    private LocalDate date;

    
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "person_id", nullable = false)
    private Person person; // Asume la clase Person

    
    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ProductoFactura> productBills = new HashSet<>(); // Asume la clase ProductoFactura
}
