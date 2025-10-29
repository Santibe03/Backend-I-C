package com.proyectoT.sena.repositoryes;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoT.sena.models.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Long> {

}
