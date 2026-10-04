package dev.mednikov.persona.personas.services;

import dev.mednikov.persona.personas.domain.CreatePersonaRequestDto;
import dev.mednikov.persona.personas.domain.PersonaResponseDto;
import dev.mednikov.persona.personas.domain.UpdatePersonaRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonaService {

    PersonaResponseDto createPersona (CreatePersonaRequestDto request);

    PersonaResponseDto updatePersona (UpdatePersonaRequestDto request);

    void deletePersona (UUID personaId);

    Optional<PersonaResponseDto> getPersonaById (UUID personaId);

    List<PersonaResponseDto> getPersonas ();

}
