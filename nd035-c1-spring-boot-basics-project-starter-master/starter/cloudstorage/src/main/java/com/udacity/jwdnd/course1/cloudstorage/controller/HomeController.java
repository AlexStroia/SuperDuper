package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller()
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String homeView() {
        return "home";
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

   // @PostMapping("/home")
    public String addNote(@ModelAttribute NoteForm form, Model model) {

        String error = null;

        int rowsAdded = noteService.insert(form);
        if (rowsAdded < 0) {
            error = "There was an error while adding this note.";
        }

        model.addAttribute(error == null ? "uploadNoteSuccess" : "uploadNoteError", error == null ? "Your file has been added." : error);

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

  //  @PostMapping("/home")
    public String editNote(@ModelAttribute NoteForm form, Model model) {

        String error = null;

        int noteEdit = noteService.edit(form);
        if (noteEdit < 0) {
            error = "There was an error while editing this note.";
        }

        model.addAttribute(error == null ? "uploadNoteSuccess" : "uploadNoteError", error == null ? "Your file has been edited." : error);

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
