package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
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

    private final UserService userService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public CredentialController(UserService userService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/credential")
    public String credentialView(Authentication authentication, NoteForm noteForm, FileForm fileForm, CredentialForm form, Model model) {
        model.addAttribute("credentials", credentialService.getAll(getUserId(authentication)));
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }

    @PostMapping("/credential/save-credential")
    public String addCredential(CredentialForm form, NoteForm noteForm, FileForm fileForm, Authentication authentication, Model model) {
        System.out.println("Credential is " + form.toString());
        String error = null;
        boolean isEdit = false;

        List<Credential> credentials = credentialService.getAll(getUserId(authentication));
        for (Credential credential : credentials) {
            if (credential.getCredentialId().equals(form.getCredentialId())) {
                isEdit = true;
                break;
            }
        }

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(form.getPassword(), encodedKey);

        form.setUserId(getUserId(authentication));
        form.setKey(encodedKey);
        form.setPassword(encryptedPassword);

        if (!isEdit) {
            int rowsAdded = credentialService.insert(form);
            if (rowsAdded < 0) {
                error = "There was an error while adding this form.";
            }
        } else {
            Credential credential = credentialService.get(getUserId(authentication));
            form.setUserId(credential.getUserId());
            credentialService.edit(form);
        }

        model.addAttribute("credentials", credentialService.getAll(getUserId(authentication)));
        model.addAttribute("encryptionService", encryptionService);

        if (error != null) {
            model.addAttribute("uploadCredentialError", error);
        } else {
            model.addAttribute("uploadCredentialSuccess","You succesfully added a new credential");
        }

        return "home";
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }

    @GetMapping("/credential/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId, NoteForm noteForm, CredentialForm credentialForm, FileForm fileform, Model model, Authentication auth) {
        int rows = credentialService.delete(credentialId);
        System.out.println("Rows" + rows);
        if(rows < 0) {
            model.addAttribute("uploadCredentialError","An error has occured while deleting this credential");
        } else {
            model.addAttribute("uploadCredentialSuccess","You successfully deleted this credential");
        }

        model.addAttribute("credentials", credentialService.getAll(getUserId(auth)));

        return "home";
    }

}
