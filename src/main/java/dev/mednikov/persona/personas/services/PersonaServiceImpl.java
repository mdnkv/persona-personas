package dev.mednikov.persona.personas.services;

import dev.mednikov.persona.personas.domain.CreatePersonaRequestDto;
import dev.mednikov.persona.personas.domain.PersonaResponseDto;
import dev.mednikov.persona.personas.domain.UpdatePersonaRequestDto;
import dev.mednikov.persona.personas.exceptions.PersonaDoesNotExistException;
import dev.mednikov.persona.personas.mappers.PersonaResponseDtoMapper;
import dev.mednikov.persona.personas.models.Persona;
import dev.mednikov.persona.personas.models.PersonaRelationshipType;
import dev.mednikov.persona.personas.repositories.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;
    private final PersonaResponseDtoMapper mapper;

    public PersonaServiceImpl(PersonaRepository personaRepository, PersonaResponseDtoMapper mapper) {
        this.personaRepository = personaRepository;
        this.mapper = mapper;
    }

    @Override
    public PersonaResponseDto createPersona(CreatePersonaRequestDto request) {
        Persona persona = new Persona();
        persona.setName(request.name());
        persona.setActive(true);
        persona.setGender(request.gender());
        persona.setRelationshipType(PersonaRelationshipType.FRIENDSHIP);

        Persona result = this.personaRepository.save(persona);

        // TODO emit persona created event

        return this.mapper.toDto(result);
    }

    @Override
    public PersonaResponseDto updatePersona(UpdatePersonaRequestDto request) {
        Persona persona = this.personaRepository.findById(request.id())
                .orElseThrow(() -> new PersonaDoesNotExistException(request.id()));
        persona.setName(request.name());
        persona.setBackstory(request.backstory());
        persona.setGender(request.gender());
        persona.setRelationshipType(request.relationshipType());

        Persona result = this.personaRepository.save(persona);

        // TODO emit persona updated event

        return this.mapper.toDto(result);
    }

    @Override
    public void deletePersona(UUID personaId) {
        this.personaRepository.deleteById(personaId);
        // TODO emit persona deleted event
    }

    @Override
    public Optional<PersonaResponseDto> getPersonaById(UUID personaId) {
        return this.personaRepository.findById(personaId).map(mapper::toDto);
    }

    @Override
    public List<PersonaResponseDto> getPersonas() {
        return this.personaRepository.findAll().stream().map(mapper::toDto).toList();
    }
}
