package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper mapper;

    public NoteService(NoteMapper mapper) {
        this.mapper = mapper;
    }

    public Note get(int id) {
        return mapper.get(id);
    }

    public List<Note> getAll() {
        return mapper.getAll();
    }

    public int delete(int id) {
        return mapper.delete(id);
    }

   public int edit(NoteForm note) {
        return mapper.edit(new Note(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription(), note.getUserId()));
    }

    public int insert(NoteForm note) {
        return mapper.insert(new Note(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription(), note.getUserId()));
    }

}