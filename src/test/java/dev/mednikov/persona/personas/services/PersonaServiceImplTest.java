package dev.mednikov.persona.personas.services;

import dev.mednikov.persona.personas.domain.CreatePersonaRequest;
import dev.mednikov.persona.personas.domain.PersonaResponse;
import dev.mednikov.persona.personas.domain.UpdatePersonaRequest;
import dev.mednikov.persona.personas.exceptions.PersonaDoesNotExistException;
import dev.mednikov.persona.personas.mappers.PersonaResponseMapper;
import dev.mednikov.persona.personas.models.Persona;
import dev.mednikov.persona.personas.models.PersonaGenderType;
import dev.mednikov.persona.personas.models.PersonaRelationshipType;
import dev.mednikov.persona.personas.repositories.PersonaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PersonaServiceImplTest {

    @Mock private PersonaRepository personaRepository;

    private PersonaServiceImpl personaService;

    @BeforeEach
    void setup(){
        PersonaResponseMapper mapper = Mappers.getMapper(PersonaResponseMapper.class);
        this.personaService = new PersonaServiceImpl(personaRepository, mapper);
    }

    @Test
    void createPersona_successTest(){
        UUID personaId = UUID.randomUUID();
        String personaName = "Jeanette";

        Persona persona = new Persona();
        persona.setId(personaId);
        persona.setName(personaName);
        persona.setGender(PersonaGenderType.FEMALE);
        persona.setRelationshipType(PersonaRelationshipType.ROMANTIC);
        persona.setBackstory("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        persona.setActive(true);

        Mockito.when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(persona);

        CreatePersonaRequest request = new CreatePersonaRequest(personaName, PersonaGenderType.FEMALE);
        PersonaResponse result = personaService.createPersona(request);

        Assertions.assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", personaId)
                .hasFieldOrPropertyWithValue("name", personaName)
                .hasFieldOrPropertyWithValue("gender", PersonaGenderType.FEMALE)
                .hasFieldOrPropertyWithValue("relationshipType", PersonaRelationshipType.ROMANTIC)
                .hasFieldOrPropertyWithValue("backstory", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                .hasFieldOrPropertyWithValue("active", true);

    }

    @Test
    void updatePersona_doesNotExistTest(){
        UUID personaId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String personaName = "Elisa";

        UpdatePersonaRequest request = new UpdatePersonaRequest(
                personaId,
                personaName,
                PersonaRelationshipType.ROMANTIC,
                PersonaGenderType.FEMALE,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        );

        Mockito.when(personaRepository.findById(personaId)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> personaService.updatePersona(request)).isInstanceOf(PersonaDoesNotExistException.class);
    }


    @Test
    void updatePersona_successTest(){
        UUID personaId = UUID.randomUUID();
        String personaName = "Iris";

        Persona persona = new Persona();
        persona.setId(personaId);
        persona.setName(personaName);
        persona.setGender(PersonaGenderType.FEMALE);
        persona.setRelationshipType(PersonaRelationshipType.ROMANTIC);
        persona.setBackstory("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        persona.setActive(true);

        UpdatePersonaRequest request = new UpdatePersonaRequest(
                personaId,
                personaName,
                PersonaRelationshipType.ROMANTIC,
                PersonaGenderType.FEMALE,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        );

        Mockito.when(personaRepository.findById(personaId)).thenReturn(Optional.of(persona));
        Mockito.when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(persona);

        PersonaResponse result = personaService.updatePersona(request);

        Assertions.assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", personaId)
                .hasFieldOrPropertyWithValue("name", personaName)
                .hasFieldOrPropertyWithValue("gender", PersonaGenderType.FEMALE)
                .hasFieldOrPropertyWithValue("relationshipType", PersonaRelationshipType.ROMANTIC)
                .hasFieldOrPropertyWithValue("backstory", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                .hasFieldOrPropertyWithValue("active", true);

    }

    @Test
    void getPersonaById_existsTest(){
        UUID personaId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String personaName = "Carrie";

        Persona persona = new Persona();
        persona.setId(personaId);
        persona.setName(personaName);
        persona.setGender(PersonaGenderType.FEMALE);
        persona.setRelationshipType(PersonaRelationshipType.ROMANTIC);
        persona.setBackstory("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        persona.setActive(true);

        Mockito.when(personaRepository.findById(personaId)).thenReturn(Optional.of(persona));

        Optional<PersonaResponse> result = personaService.getPersonaById(personaId);

        Assertions.assertThat(result).isNotNull().isPresent();
    }

    @Test
    void getPersonaById_doesNotExistTest(){
        UUID personaId = UUID.randomUUID();
        Mockito.when(personaRepository.findById(personaId)).thenReturn(Optional.empty());

        Optional<PersonaResponse> result = personaService.getPersonaById(personaId);

        Assertions.assertThat(result).isNotNull().isEmpty();
    }

}
