package com.proyectoT.sena.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Medidas {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_Medida")
    private Long id;

    @Column(length = 20)
    private String medNam;

    @Column(length = 5)
    private String abreviacion;

}
