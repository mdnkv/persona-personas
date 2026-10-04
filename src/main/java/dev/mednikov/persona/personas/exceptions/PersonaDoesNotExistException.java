package dev.mednikov.persona.personas.exceptions;

import java.util.UUID;

public final class PersonaDoesNotExistException extends RuntimeException{

    public PersonaDoesNotExistException(UUID personaId){
        super("Persona with id " + personaId.toString() + " does not exist");
    }

}
