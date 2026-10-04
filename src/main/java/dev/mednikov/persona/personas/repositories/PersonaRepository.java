package dev.mednikov.persona.personas.repositories;

import dev.mednikov.persona.personas.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, UUID> {

}
