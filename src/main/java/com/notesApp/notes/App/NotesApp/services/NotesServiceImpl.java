package com.notesApp.notes.App.NotesApp.services;

import com.notesApp.notes.App.NotesApp.DTOs.NotesRequestDTO;
import com.notesApp.notes.App.NotesApp.DTOs.NotesResponseDTO;
import com.notesApp.notes.App.NotesApp.exceptions.NoteNotFoundException;
import com.notesApp.notes.App.NotesApp.models.Note;
import com.notesApp.notes.App.NotesApp.repositories.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotesServiceImpl implements NotesService{
    private NotesRepository notesRepository;
    public NotesServiceImpl(NotesRepository notesRepository){
        this.notesRepository = notesRepository;
    }

    @Override
    public List<NotesResponseDTO> getAllNotes() {
        List<Note> notes =  notesRepository.findAll();
        List<NotesResponseDTO> notesResponseDTOList = new ArrayList<>();
        for(Note note : notes){
            NotesResponseDTO notesResponseDTO = convertNoteToNoteResponseDTO(note);
            notesResponseDTOList.add(notesResponseDTO);
        }
        return notesResponseDTOList;
    }

    @Override
    public NotesResponseDTO getNotesById(Long id) throws NoteNotFoundException {
        Optional<Note> note = notesRepository.findById(id);
        NotesResponseDTO notesResponseDTO = new NotesResponseDTO();
        if(note.isPresent()){
            Note currNote = note.get();
            notesResponseDTO = convertNoteToNoteResponseDTO(currNote);
            return notesResponseDTO;
        }
        throw new NoteNotFoundException("note with id "+id+" not found");
    }

    @Override
    public NotesResponseDTO postNotes(NotesRequestDTO notesRequestDTO) {
        Note note = new Note();
        note.setTitle(notesRequestDTO.getTitle());
        note.setDescription(notesRequestDTO.getDescription());
        note.setUserId(notesRequestDTO.getUserId());
        Long userId = notesRequestDTO.getUserId();
        Note currNote = notesRepository.save(note);
        NotesResponseDTO notesResponseDTO = convertNoteToNoteResponseDTO(currNote);
        return notesResponseDTO;
    }

    @Override
    public NotesResponseDTO updateNotes(NotesRequestDTO notesRequestDTO, Long id) throws NoteNotFoundException {
        Optional<Note> note = notesRepository.findById(id);
        if(note.isPresent()){
            Note currNote = note.get();
            Long userId = notesRequestDTO.getUserId();
            currNote.setTitle(notesRequestDTO.getTitle());
            currNote.setUserId(notesRequestDTO.getUserId());
            currNote.setDescription(notesRequestDTO.getDescription());
            Note updatedNote = notesRepository.save(currNote);
            return convertNoteToNoteResponseDTO(updatedNote);
            }
        throw new NoteNotFoundException("note with id "+id+" not found");
    }

    @Override
    public void deleteNotes(Long id) throws NoteNotFoundException {
        Optional<Note> note = notesRepository.findById(id);
        if(note.isPresent()){
           notesRepository.delete(note.get());
           return;
        }
        throw new NoteNotFoundException("note with id "+id+" not found");
    }
    public NotesResponseDTO convertNoteToNoteResponseDTO(Note note){
        NotesResponseDTO notesResponseDTO = new NotesResponseDTO();
        notesResponseDTO.setId(note.getId());
        notesResponseDTO.setTitle(note.getTitle());
        notesResponseDTO.setDescription(note.getDescription());
        notesResponseDTO.setUserId(note.getUserId());
        return notesResponseDTO;
    }
}
