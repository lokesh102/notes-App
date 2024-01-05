package com.notesApp.notes.App.NotesApp.controllers;

import com.notesApp.notes.App.NotesApp.DTOs.NotesRequestDTO;
import com.notesApp.notes.App.NotesApp.DTOs.NotesResponseDTO;
import com.notesApp.notes.App.NotesApp.aspects.RateLimited;
import com.notesApp.notes.App.NotesApp.exceptions.NoteNotFoundException;
import com.notesApp.notes.App.NotesApp.models.Note;
import com.notesApp.notes.App.NotesApp.services.NotesService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    private NotesService notesService;

    public NotesController(NotesService notesService){
        this.notesService = notesService;
    }
    @GetMapping("/search")
    @RateLimited
    public List<NotesResponseDTO> getProductsBySearch(@RequestParam String q){
        String title = q;
        List<NotesResponseDTO> notesResponseDTOList = notesService.getProductsBySearch(title);
        return notesResponseDTOList;
    }
    @GetMapping()
    @RateLimited
    public ResponseEntity<List<NotesResponseDTO>> getAllNotes(){
        List<NotesResponseDTO> notesResponseDTOList = notesService.getAllNotes();
        return new ResponseEntity<>(notesResponseDTOList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @RateLimited
    public NotesResponseDTO getNotesById(@PathVariable Long id) throws NoteNotFoundException {
        NotesResponseDTO notesResponseDTO = notesService.getNotesById(id);
        return notesResponseDTO;
    }
    @PostMapping()
    @RateLimited
    public NotesResponseDTO postNotes(@RequestBody NotesRequestDTO notesRequestDTO) {
        NotesResponseDTO notesResponseDTO = notesService.postNotes(notesRequestDTO);
        return notesResponseDTO;
    }
    @PatchMapping("/{id}")
    @RateLimited
    public NotesResponseDTO updateNotes(@RequestBody NotesRequestDTO notesRequestDTO, @PathVariable Long id) throws NoteNotFoundException {
        NotesResponseDTO notesResponseDTO = notesService.updateNotes(notesRequestDTO,id);
        return notesResponseDTO;
    }
    @DeleteMapping("/{id}")
    @RateLimited
    public void deleteNotes(@PathVariable Long id) throws NoteNotFoundException {
        notesService.deleteNotes(id);
    }
}
