package com.boljshoj.StudentsJournal.controllers;

import com.boljshoj.StudentsJournal.domain.Student;
import com.boljshoj.StudentsJournal.domain.Task;
import com.boljshoj.StudentsJournal.repository.StudentsRepo;
import com.boljshoj.StudentsJournal.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tasks")
public class TaskContoller {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private StudentsRepo studentsRepo;

    @GetMapping
    private String tasksList(Model model){
        model.addAttribute("tasks", taskRepo.findAll());
        return "tasks";
    }

    @PostMapping
    public String addTask(
            @RequestParam String taskName,
            @RequestParam String fileLocation,
            Model model
    ){
        Iterable<Student> students = studentsRepo.findAll();
        for (Student s : students){
            Task task = new Task(taskName, fileLocation, s);
            taskRepo.save(task);
        }

        model.addAttribute("tasks", taskRepo.findAll());
        return "tasks";
    }

}