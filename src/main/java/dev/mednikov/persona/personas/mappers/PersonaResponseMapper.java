package dev.mednikov.persona.personas.mappers;

import dev.mednikov.persona.personas.domain.PersonaResponse;
import dev.mednikov.persona.personas.models.Persona;
import org.mapstruct.Mapper;

@Mapper
public interface PersonaResponseMapper {

    PersonaResponse toDto (Persona persona);

}
