package com.boljshoj.StudentsJournal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class ErrorsController {

    @GetMapping
    public String errorsList(){
        return "errors";
    }
}