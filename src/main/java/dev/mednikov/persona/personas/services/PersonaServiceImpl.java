package dev.mednikov.persona.personas.services;

import dev.mednikov.persona.personas.domain.CreatePersonaRequest;
import dev.mednikov.persona.personas.domain.PersonaResponse;
import dev.mednikov.persona.personas.domain.UpdatePersonaRequest;
import dev.mednikov.persona.personas.exceptions.PersonaDoesNotExistException;
import dev.mednikov.persona.personas.mappers.PersonaResponseMapper;
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
    private final PersonaResponseMapper mapper;

    public PersonaServiceImpl(PersonaRepository personaRepository, PersonaResponseMapper mapper) {
        this.personaRepository = personaRepository;
        this.mapper = mapper;
    }

    @Override
    public PersonaResponse createPersona(CreatePersonaRequest request) {
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
    public PersonaResponse updatePersona(UpdatePersonaRequest request) {
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
    public Optional<PersonaResponse> getPersonaById(UUID personaId) {
        return this.personaRepository.findById(personaId).map(mapper::toDto);
    }

    @Override
    public List<PersonaResponse> getPersonas() {
        return this.personaRepository.findAll().stream().map(mapper::toDto).toList();
    }
}
