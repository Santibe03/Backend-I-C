package com.proyectoT.sena.repositoryes;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoT.sena.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
