package com.notesApp.notes.App.NotesApp.repositories;

import com.notesApp.notes.App.NotesApp.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note,Long> {
    List<Note> findAll();
}
