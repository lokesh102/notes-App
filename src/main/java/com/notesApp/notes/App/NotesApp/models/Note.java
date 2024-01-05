package com.notesApp.notes.App.NotesApp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "notes")
@JsonDeserialize
public class Note extends BaseModel implements Serializable {
    private String title;
    private String description;
    private Long userId;
}
