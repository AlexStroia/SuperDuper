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

    public List<Note> getAll(int id) {
        return mapper.getAll(id);
    }

    public int delete(int id) {
        return mapper.delete(id);
    }

    public void edit(NoteForm note) {
        mapper.edit(note.getNoteId(),note.getNoteTitle(),note.getNoteDescription());
    }

    public int insert(NoteForm note) {
        Note newNote = new Note();
        newNote.setNotetitle(note.getNoteTitle());
        newNote.setNotedescription(note.getNoteDescription());
        newNote.setUserid(note.getUserId());
        return mapper.insert(newNote);
    }

}