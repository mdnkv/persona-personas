package dev.mednikov.persona.personas.mappers;

import dev.mednikov.persona.personas.domain.PersonaResponseDto;
import dev.mednikov.persona.personas.models.Persona;
import org.mapstruct.Mapper;

@Mapper
public interface PersonaResponseDtoMapper {

    PersonaResponseDto toDto (Persona persona);

}
