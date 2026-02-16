package com.proyectoT.sena.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoT.sena.models.InsumosProducto;

public interface InsumosProductRepository extends JpaRepository<InsumosProducto, Long> {
    java.util.List<InsumosProducto> findAllByProductRestauranteId(Long restauranteId);
}
