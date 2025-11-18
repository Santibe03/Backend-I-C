package com.proyectoT.sena.repositoryes;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoT.sena.models.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
