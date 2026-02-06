package com.proyectoT.sena.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRestauranteId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "restaurante_id")
    private Long restauranteId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserRestauranteId that = (UserRestauranteId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(restauranteId, that.restauranteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, restauranteId);
    }
}
