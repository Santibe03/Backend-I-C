package com.proyectoT.sena.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.proyectoT.sena.models.Producto;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p LEFT JOIN FETCH p.productInputs pi LEFT JOIN FETCH pi.input WHERE p.id = :id")
    Optional<Producto> findByIdWithRecipe(@Param("id") Long id);
}
