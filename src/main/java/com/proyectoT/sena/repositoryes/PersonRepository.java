package com.proyectoT.sena.repositoryes;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoT.sena.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
