package com.proyectoT.sena.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarraMesaDTO {

    private Long id;
    private com.proyectoT.sena.models.enums.TableAvailability availability;
    private Integer share;
    private Long restauranteId;

    // Explicit getter/setter to ensure compilation
    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }
}
