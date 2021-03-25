package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/")
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;

    @GetMapping
    public String homeView(Authentication authentication, NoteForm noteForm, CredentialForm form, Model model) {
        model.addAttribute("notes", noteService.getAll(getUserId(authentication)));
        model.addAttribute("credentials", credentialService.getAll(getUserId(authentication)));
        model.addAttribute("encryptionService", encryptionService);
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

    @ModelAttribute("navigation")
    public String[] allMessageTypes() {
        return new String[]{"Files", "Notes", "Credentials"};
    }
}
