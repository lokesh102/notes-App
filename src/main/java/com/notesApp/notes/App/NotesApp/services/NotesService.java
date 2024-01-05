package com.notesApp.notes.App.NotesApp.services;

import com.notesApp.notes.App.NotesApp.DTOs.NotesRequestDTO;
import com.notesApp.notes.App.NotesApp.DTOs.NotesResponseDTO;
import com.notesApp.notes.App.NotesApp.exceptions.NoteNotFoundException;

import java.util.List;

public interface NotesService {
    List<NotesResponseDTO> getAllNotes();
    NotesResponseDTO getNotesById(Long Id) throws NoteNotFoundException;
    NotesResponseDTO postNotes( NotesRequestDTO notesRequestDTO);
    NotesResponseDTO updateNotes(NotesRequestDTO notesRequestDTO, Long id) throws NoteNotFoundException;
    void deleteNotes(Long id) throws NoteNotFoundException;
}
