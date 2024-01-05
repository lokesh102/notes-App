package com.notesApp.notes.App.NotesApp.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotesRequestDTO {
    private String title;
    private String description;
    private Long userId;
}
