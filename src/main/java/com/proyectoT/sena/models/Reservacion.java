package com.proyectoT.sena.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.concurrent.locks.Condition;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reservation") 
public class Reservacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservacion")
    private Long id;

    @Column(name = "aplication_date")
    private LocalDate aplicationDate;

    @Column(name = "reservation_date")
    private ZonedDateTime reservationDate;

    @Column(name = "attendat")
    private Integer attendat;

   
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "person_id", nullable = false)
    private Person person; 

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "condition_id", nullable = false)
    private Condicion condition; 

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "bar_table_id", nullable = false)
    private BarraMesa barTable; 
}
