package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/note")
public class NoteController {

    @GetMapping
    public String noteView() {
        return "note";
    }
}
