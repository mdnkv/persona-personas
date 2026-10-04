package dev.mednikov.persona.personas.domain;

import dev.mednikov.persona.personas.models.PersonaGenderType;

public record CreatePersonaRequestDto(String name, PersonaGenderType gender) {
}
