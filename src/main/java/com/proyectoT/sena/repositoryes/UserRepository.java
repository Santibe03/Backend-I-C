package com.proyectoT.sena.repositoryes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoT.sena.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);
}