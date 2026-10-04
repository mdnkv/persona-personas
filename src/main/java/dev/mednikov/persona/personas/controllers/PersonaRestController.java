package dev.mednikov.persona.personas.controllers;

import dev.mednikov.persona.personas.domain.CreatePersonaRequestDto;
import dev.mednikov.persona.personas.domain.PersonaResponseDto;
import dev.mednikov.persona.personas.domain.UpdatePersonaRequestDto;
import dev.mednikov.persona.personas.services.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/personas")
@CrossOrigin("*")
public class PersonaRestController {

    private final PersonaService personaService;

    public PersonaRestController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody PersonaResponseDto createPersona (@RequestBody CreatePersonaRequestDto body){
        return this.personaService.createPersona(body);
    }

    @PutMapping
    public @ResponseBody PersonaResponseDto updatePersona (@RequestBody UpdatePersonaRequestDto body){
        return this.personaService.updatePersona(body);
    }

    @DeleteMapping("/{personaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersona (@PathVariable UUID personaId){
        this.personaService.deletePersona(personaId);
    }

    @GetMapping("/{personaId}")
    public ResponseEntity<PersonaResponseDto> getPersonaById (@PathVariable UUID personaId){
        Optional<PersonaResponseDto> result = this.personaService.getPersonaById(personaId);
        return ResponseEntity.of(result);
    }

    @GetMapping
    public @ResponseBody List<PersonaResponseDto> getPersonas (){
        return this.personaService.getPersonas();
    }

}
