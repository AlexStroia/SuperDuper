package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/file")
public class FileController {

    @GetMapping("/file")
    public String fileView(Authentication authentication, NoteForm noteForm, CredentialForm form, Model model) {
        model.addAttribute("activePage", "file");
        return "home";
    }
}
