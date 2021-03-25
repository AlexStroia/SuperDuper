package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller("/file")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping("/file")
    public String fileView(Authentication authentication, NoteForm noteForm, FileForm fileForm, CredentialForm form, Model model) {
        return "home";
    }

    @PostMapping("/file/upload")
    public String addNote(NoteForm noteForm, CredentialForm form, FileForm fileForm, Model model, Authentication auth) throws IOException {
        boolean isNameUnique = true;

        List<File> files = fileService.getAll(getUserId(auth));
        for (File file : files) {
            if (file.getFileName().equals(fileForm.getMultipartFile().getOriginalFilename())) {
                isNameUnique = false;
                model.addAttribute("fileFormError", "Please upload a file with a different name");
            }
        }

        if (isNameUnique) {
            fileForm.setFileName(fileForm.getMultipartFile().getOriginalFilename());
            fileForm.setUserId(getUserId(auth));
            fileForm.setContentType(fileForm.getMultipartFile().getContentType());
            fileForm.setFileSize(String.valueOf(fileForm.getMultipartFile().getSize()));
            fileService.insert(fileForm);
        }
        model.addAttribute("files", fileService.getAll(getUserId(auth)));
        return "home";
    }

    @GetMapping("/file/delete{filename}")
    public String deleteFile(@PathVariable String filename, NoteForm noteForm, CredentialForm form, FileForm fileForm, Model model, Authentication auth) throws IOException {
        fileService.delete(filename);
        model.addAttribute("files", fileService.getAll(getUserId(auth)));
        return "home";
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }
}
