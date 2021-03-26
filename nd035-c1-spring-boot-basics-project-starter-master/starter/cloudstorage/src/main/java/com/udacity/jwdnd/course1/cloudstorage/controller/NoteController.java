package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller("/note")
public class NoteController {

    private List<Note> notes;
    private final UserService userService;
    private final NoteService noteService;
    private final EncryptionService encryptionService;


    public NoteController(FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.noteService = noteService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostConstruct
    public void postContruct() {
        notes = new ArrayList<>();
    }

    @GetMapping("/note")
    public String noteView(Authentication authentication, NoteForm noteForm, FileForm fileForm, CredentialForm form, Model model) {
        model.addAttribute("notes", noteService.getAll(getUserId(authentication)));
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }

    @PostMapping("/note/save-note")
    public String addNote(Authentication auth, NoteForm noteForm, FileForm fileForm, CredentialForm form, Model model) {

        String error = null;
        int rowsAdded = 0;

        noteForm.setUserId(getUserId(auth));
        if (noteForm.getNoteId() != null) {
            noteService.edit(noteForm);
        } else {
            rowsAdded = noteService.insert(noteForm);
        }
        if (rowsAdded < 0) {
            error = "There was an error while adding this note.";
        }

        notes = noteService.getAll(getUserId(auth));
        model.addAttribute("notes", notes);
        model.addAttribute("encryptionService", encryptionService);

        if(error == null) {
            model.addAttribute("uploadNoteSuccess", true);
        } else {
            model.addAttribute("uploadNoteError", error);
        }

        return "home";
    }

    @GetMapping("/note/delete-note/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, NoteForm noteForm, CredentialForm credentialForm, FileForm form,Model model, Authentication auth) {

        noteService.delete(noteId);
        notes = noteService.getAll(getUserId(auth));
        model.addAttribute("notes", notes);

        return "home";
    }
}
