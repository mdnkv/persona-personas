package dev.mednikov.persona.personas.controllers;

import dev.mednikov.persona.personas.domain.CreatePersonaRequest;
import dev.mednikov.persona.personas.domain.PersonaResponse;
import dev.mednikov.persona.personas.domain.UpdatePersonaRequest;
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
    public @ResponseBody PersonaResponse createPersona (@RequestBody CreatePersonaRequest body){
        return this.personaService.createPersona(body);
    }

    @PutMapping
    public @ResponseBody PersonaResponse updatePersona (@RequestBody UpdatePersonaRequest body){
        return this.personaService.updatePersona(body);
    }

    @DeleteMapping("/{personaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersona (@PathVariable UUID personaId){
        this.personaService.deletePersona(personaId);
    }

    @GetMapping("/{personaId}")
    public ResponseEntity<PersonaResponse> getPersonaById (@PathVariable UUID personaId){
        Optional<PersonaResponse> result = this.personaService.getPersonaById(personaId);
        return ResponseEntity.of(result);
    }

    @GetMapping
    public @ResponseBody List<PersonaResponse> getPersonas (){
        return this.personaService.getPersonas();
    }

}
