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
public class Mesa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_mesa")
    private Long id;

    private Integer num;
    private Integer cap;  
    private Boolean dis;

}
