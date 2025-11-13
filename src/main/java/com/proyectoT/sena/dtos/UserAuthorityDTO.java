package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthorityDTO {
    private Long userId;
    private String authorityName;
    private Instant grantedDate;
}
