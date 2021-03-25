package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Controller()
@RequestMapping("/")
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final UserService userService;

    private List<File> files;
    private List<Note> notes;
    private List<Credential> credentials;

    @PostConstruct
    public void postContruct() {
        files = new ArrayList<>();
        notes = new ArrayList<>();
        credentials = new ArrayList<>();
    }

    @GetMapping
    public String homeView(Authentication authentication, NoteForm noteForm, Model model) {
        model.addAttribute("notes", noteService.getAll(getUserId(authentication)));
        System.out.println("Notes are " + noteService.getAll(getUserId(authentication)));

        return "home";
    }

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }

    //PostMapping("/home")
    public String addFile(@ModelAttribute FileForm form, Model model) {

        String error = null;

        int rowsAdded = fileService.insert(form);
        if (rowsAdded < 0) {
            error = "There was an error while adding this file.";
        }

        model.addAttribute(error == null ? "uploadFileSuccess" : "uploadFileError", error == null ? "Your file has been added." : error);

        return "home";
    }

    @PostMapping("/save-note")
    public String addNote(NoteForm noteForm, Model model, Authentication auth) {

        String error = null;
        int rowsAdded = 0;
        System.out.println("User id is " + getUserId(auth));

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
        model.addAttribute(error == null ? "uploadNoteSuccess" : "uploadNoteError", error == null ? "Your file has been added." : error);

        return "home";
    }

    @GetMapping("/delete-note/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, NoteForm noteForm, Model model, Authentication auth) {

        noteService.delete(noteId);
        notes = noteService.getAll(getUserId(auth));
        model.addAttribute("notes", notes);

        return "home";
    }

    //   @PostMapping("/home")
    public String addCredential(@ModelAttribute CredentialForm form, Model model) {

        String error = null;

        int rowsAdded = credentialService.insert(form);
        if (rowsAdded < 0) {
            error = "There was an error while adding this form.";
        }

        model.addAttribute(error == null ? "uploadCredentialSuccess" : "uploadCredentialError", error == null ? "Your credential has been added." : error);

        return "home";
    }

    //    @PostMapping("/home")
    public String editCredential(@ModelAttribute CredentialForm form, Model model) {

        String error = null;

        int noteEdit = credentialService.edit(form);
        if (noteEdit < 0) {
            error = "There was an error while editing this form.";
        }

        model.addAttribute(error == null ? "uploadCredentialSuccess" : "uploadCredentialError", error == null ? "Your credential has been saved" : error);

        return "home";
    }

    //    @PostMapping("/home")
    public String editFile(@ModelAttribute FileForm form, Model model) {

        String error = null;

        int rowsEdited = fileService.edit(form);
        if (rowsEdited < 0) {
            error = "There was an error while adding this file.";
        }

        model.addAttribute(error == null ? "editSuccess" : "editError", error == null ? "Your file has been edited" : error);

        return "home";
    }

}
