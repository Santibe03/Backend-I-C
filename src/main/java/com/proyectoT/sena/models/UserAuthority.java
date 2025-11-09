package com.proyectoT.sena.models;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "app_user_authority")
public class UserAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserAuthorityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("authorityName")
    @JoinColumn(name = "authority_name")
    private Authority authority;

    // Campo adicional opcional
    @Column(name = "granted_date")
    private Instant grantedDate = Instant.now();
}

