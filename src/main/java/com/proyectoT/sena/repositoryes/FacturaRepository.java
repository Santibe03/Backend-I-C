package com.proyectoT.sena.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyectoT.sena.models.Factura;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

    @Query("SELECT f FROM Factura f LEFT JOIN FETCH f.person LEFT JOIN FETCH f.productBills WHERE f.id = :id")
    Optional<Factura> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT DISTINCT f FROM Factura f LEFT JOIN FETCH f.person")
    List<Factura> findAllWithPerson();
}
