package com.proyectoT.sena.models;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthorityId implements Serializable {
    private Long userId;
    private String authorityName;

    
}
