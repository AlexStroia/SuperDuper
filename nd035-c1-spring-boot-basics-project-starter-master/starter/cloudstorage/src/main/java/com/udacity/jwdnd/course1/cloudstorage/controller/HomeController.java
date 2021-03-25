package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Controller()
@RequestMapping("/")
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;

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
    public String homeView(Authentication authentication, NoteForm noteForm, CredentialForm form, Model model) {
        model.addAttribute("notes", noteService.getAll(getUserId(authentication)));
        model.addAttribute("credentials", credentialService.getAll(getUserId(authentication)));
        System.out.println("Notes are " + noteService.getAll(getUserId(authentication)));

        return "home";
    }

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
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
    public String addNote(NoteForm noteForm, CredentialForm form, Model model, Authentication auth) {

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
        model.addAttribute(error == null ? "uploadNoteSuccess" : "uploadNoteError", error == null ? "Your file has been added." : error);

        return "home";
    }

    @GetMapping("/delete-note/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, NoteForm noteForm, CredentialForm credentialForm, Model model, Authentication auth) {

        noteService.delete(noteId);
        notes = noteService.getAll(getUserId(auth));
        model.addAttribute("notes", notes);

        return "home";
    }

    @PostMapping("/save-credential")
    public String addCredential(CredentialForm form, NoteForm noteForm, Authentication authentication, Model model) {

        String error = null;

        if (form.getCredentialId() != null) {
            encryptCredentials(form, authentication);

            int rowsAdded = credentialService.insert(form);
            if (rowsAdded < 0) {
                error = "There was an error while adding this form.";
            }
        } else {
            encryptCredentials(form, authentication);
            credentialService.edit(form);
        }

        credentials = credentialService.getAll(getUserId(authentication));

        model.addAttribute(error == null ? "uploadCredentialSuccess" : "uploadCredentialError", error == null ? "Your credential has been added." : error);

        return "home";
    }

    private void encryptCredentials(CredentialForm form, Authentication authentication) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(form.getPassword(), encodedKey);

        form.setUserId(getUserId(authentication));
        form.setKey(encodedKey);
        form.setPassword(encryptedPassword);
    }

    @GetMapping("/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId, NoteForm noteForm, CredentialForm credentialForm, Model model, Authentication auth) {

        credentialService.delete(credentialId);
        credentials = credentialService.getAll(getUserId(auth));
        model.addAttribute("credentials", credentials);

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
