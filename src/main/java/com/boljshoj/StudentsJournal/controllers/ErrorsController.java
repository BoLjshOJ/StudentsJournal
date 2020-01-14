package com.boljshoj.StudentsJournal.controllers;

import com.boljshoj.StudentsJournal.domain.Student;
import com.boljshoj.StudentsJournal.domain.Task;
import com.boljshoj.StudentsJournal.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/errors")
public class ErrorsController {

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping
    public String errorsList(){
        return "errors";
    }

    @GetMapping("{student}")
    public String getUsersErrors(@PathVariable Student student, Model model){
        List<Task> errors = taskRepo.findTasksByStudentAndIsResolvedFalse(student);
        model.addAttribute("errors", errors);
        return "errors";
    }
}