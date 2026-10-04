package dev.mednikov.persona.personas.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "personas_persona")
public class Persona {

    @Id @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    private UUID id;

    @Column(name = "persona_name", nullable = false)
    private String name;

    @Column(name = "relationship_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonaRelationshipType relationshipType;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonaGenderType gender;

    @Column(name = "backstory")
    private String backstory;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonaRelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(PersonaRelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public PersonaGenderType getGender() {
        return gender;
    }

    public void setGender(PersonaGenderType gender) {
        this.gender = gender;
    }

    public String getBackstory() {
        return backstory;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
