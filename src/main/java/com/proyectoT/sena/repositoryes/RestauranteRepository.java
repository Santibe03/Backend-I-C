package com.proyectoT.sena.repositoryes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoT.sena.models.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByActivoTrue();

    List<Restaurante> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);

    Optional<Restaurante> findByNombreAndActivoTrue(String nombre);

    Optional<Restaurante> findByIdAndActivoTrue(Long id);
}
