package dev.mednikov.persona.personas.domain;

import dev.mednikov.persona.personas.models.PersonaGenderType;
import dev.mednikov.persona.personas.models.PersonaRelationshipType;

import java.util.UUID;

public record UpdatePersonaRequest(
        UUID id,
        String name,
        PersonaRelationshipType relationshipType,
        PersonaGenderType gender,
        String backstory
) {
}
