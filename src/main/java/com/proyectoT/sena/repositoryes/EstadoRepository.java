package com.proyectoT.sena.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoT.sena.models.Condicion;

public interface EstadoRepository extends JpaRepository<Condicion,Long> {

}
