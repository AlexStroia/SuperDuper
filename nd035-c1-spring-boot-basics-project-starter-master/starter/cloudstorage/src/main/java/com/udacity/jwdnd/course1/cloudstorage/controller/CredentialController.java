package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Controller("/credential")
public class CredentialController {

    private List<Credential> credentials;
    private final UserService userService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public CredentialController(UserService userService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/credential")
    public String credentialView(Authentication authentication, NoteForm noteForm,FileForm fileForm, CredentialForm form, Model model) {
        model.addAttribute("credentials", credentialService.getAll(getUserId(authentication)));
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }

    @PostMapping("/credential/save-credential")
    public String addCredential(CredentialForm form, NoteForm noteForm, FileForm fileForm, Authentication authentication, Model model) {

        String error = null;
        encryptCredentials(form, authentication);

        if (form.getCredentialId() == null) {
            int rowsAdded = credentialService.insert(form);
            if (rowsAdded < 0) {
                error = "There was an error while adding this form.";
            }
        } else {
            credentialService.edit(form);
        }

        credentials = credentialService.getAll(getUserId(authentication));
        model.addAttribute("credentials", credentials);
        model.addAttribute("encryptionService", encryptionService);

        if(error == null) {
            model.addAttribute("uploadCredentialSuccess", true);
        } else {
            model.addAttribute("uploadCredentialError", error);
        }

        return "home";
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
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

    @GetMapping("/credential/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId, NoteForm noteForm, CredentialForm credentialForm, Model model, Authentication auth) {

        credentialService.delete(credentialId);
        credentials = credentialService.getAll(getUserId(auth));
        model.addAttribute("credentials", credentials);

        return "home";
    }

}
