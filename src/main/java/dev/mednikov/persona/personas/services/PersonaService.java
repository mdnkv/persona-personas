package dev.mednikov.persona.personas.services;

import dev.mednikov.persona.personas.domain.CreatePersonaRequest;
import dev.mednikov.persona.personas.domain.PersonaResponse;
import dev.mednikov.persona.personas.domain.UpdatePersonaRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonaService {

    PersonaResponse createPersona (CreatePersonaRequest request);

    PersonaResponse updatePersona (UpdatePersonaRequest request);

    void deletePersona (UUID personaId);

    Optional<PersonaResponse> getPersonaById (UUID personaId);

    List<PersonaResponse> getPersonas ();

}
