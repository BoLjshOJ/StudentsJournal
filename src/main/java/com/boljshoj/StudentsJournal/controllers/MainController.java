package com.boljshoj.StudentsJournal.controllers;

import com.boljshoj.StudentsJournal.domain.Group;
import com.boljshoj.StudentsJournal.domain.Student;
import com.boljshoj.StudentsJournal.domain.Task;
import com.boljshoj.StudentsJournal.domain.User;
import com.boljshoj.StudentsJournal.repository.StudentsRepo;
import com.boljshoj.StudentsJournal.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private StudentsRepo studentsRepo;

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/")
    public String startPage(Model model){
        return "startPage";
    }

    @GetMapping("/index")
    public String main(
            @RequestParam(required = false) String filter,
            Model model
    ){
        Iterable<Student> students;
        Iterable<Task> tasks = taskRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            students = studentsRepo.findByGroupName(filter);
        } else {
            students = studentsRepo.findAll();
        }

        model.addAttribute("students", students);
        model.addAttribute("filter", filter);
        model.addAttribute("groups", Group.values());
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @PostMapping("/index")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String fullName,
            @RequestParam String group,
            Model model
    ) {
        Student student = new Student(fullName, user, group);
        studentsRepo.save(student);
        Iterable<Student> students = studentsRepo.findAll();
        Iterable<Task> tasks = taskRepo.findAll();
        model.addAttribute("students", students);
        model.addAttribute("groups", Group.values());
        model.addAttribute("tasks", tasks);
        return "index";
    }
}