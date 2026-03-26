package com.proyectoT.sena.repositoryes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoT.sena.models.UserRestaurante;
import com.proyectoT.sena.models.UserRestauranteId;

@Repository
public interface UserRestauranteRepository extends JpaRepository<UserRestaurante, UserRestauranteId> {

    List<UserRestaurante> findByIdUserId(Long userId);

    List<UserRestaurante> findByIdUserIdAndActivoTrue(Long userId);

    List<UserRestaurante> findByIdRestauranteIdAndActivoTrue(Long restauranteId);

    List<UserRestaurante> findByIdUserIdAndEsAdministradorTrue(Long userId);

    Optional<UserRestaurante> findByIdUserIdAndIdRestauranteId(Long userId, Long restauranteId);

    boolean existsByIdRestauranteIdAndEsAdministradorTrueAndActivoTrue(Long restauranteId);

    long countByIdRestauranteIdAndEsAdministradorTrueAndActivoTrue(Long restauranteId);

    boolean existsByIdUserIdAndIdRestauranteIdAndActivoTrue(Long userId, Long restauranteId);
}
