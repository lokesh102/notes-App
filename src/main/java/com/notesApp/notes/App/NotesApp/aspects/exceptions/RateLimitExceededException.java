package com.notesApp.notes.App.NotesApp.aspects.exceptions;

public class RateLimitExceededException extends Exception{
    public RateLimitExceededException(String s){
        super(s);
    }
}
