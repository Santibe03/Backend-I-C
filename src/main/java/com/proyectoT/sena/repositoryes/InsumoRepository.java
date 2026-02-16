package com.proyectoT.sena.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoT.sena.models.Insumo;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    java.util.List<Insumo> findByRestauranteId(Long restauranteId);
}
