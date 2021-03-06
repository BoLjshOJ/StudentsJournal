package com.boljshoj.StudentsJournal.controllers;

import com.boljshoj.StudentsJournal.domain.User;
import com.boljshoj.StudentsJournal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model){
        if (!userService.addUser(user)){
            model.addAttribute("message", "User already exists");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if (isActivated){
            model.addAttribute("message", "Activation was successful");
        } else {
            model.addAttribute("message", "Activation code was not found");
        }
        return "login";
    }
}