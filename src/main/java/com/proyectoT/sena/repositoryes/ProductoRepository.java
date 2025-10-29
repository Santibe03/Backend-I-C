package com.proyectoT.sena.repositoryes;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoT.sena.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
