package com.proyectoT.sena.models;

import java.time.Instant;

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
public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name="id_user")
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 50)
    private String lastName;

    @Column(length = 254, unique = true)
    private String email;

    
    @Column(nullable = false)
    private boolean activated = false;

    @Column(length = 10)
    private String langKey;

    @Column(length = 256)
    private String imageUrl;


    @Column(length = 20)
    private String activationKey;


    @Column(length = 20)
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;


}
