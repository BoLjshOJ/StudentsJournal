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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if (filter != null && !filter.isEmpty()) {
            students = studentsRepo.findByGroupName(filter);
        } else {
            students = studentsRepo.findAll();
        }

        updateTotalCountTask();
        model.addAttribute("students", students);
        model.addAttribute("filter", filter);
        model.addAttribute("groups", Group.values());
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

        if (getDistinctListOfTasks().size() > 0) {
            if (taskRepo.findTasksByStudent(student).isEmpty() || taskRepo.findTasksByStudent(student).size() < getDistinctListOfTasks().size()) {
                for (Task t : getDistinctListOfTasks()) {
                    taskRepo.save(new Task(t.getTaskName(), t.getFileLocation(), student));
                }
            }
        }

        updateTotalCountTask();
        Iterable<Student> students = studentsRepo.findAll();

        model.addAttribute("students", students);
        model.addAttribute("groups", Group.values());
        return "index";
    }

    public void updateTotalCountTask(){
        for (Student s : studentsRepo.findAll()){
            s.setTotalTest(taskRepo.findTasksByStudent(s).size());
            studentsRepo.save(s);
        }
    }

    /** @noinspection Duplicates*/
    public List<Task> getDistinctListOfTasks(){
        Map<String, String> mapOfTaskAndTasklocation = new HashMap<>();

        taskRepo.findAll().forEach(t -> mapOfTaskAndTasklocation.put(t.getTaskName(), t.getFileLocation()));

        List<Task> listOfDistinctTask = new ArrayList<>();
        for (Map.Entry<String, String> m : mapOfTaskAndTasklocation.entrySet()){
            listOfDistinctTask.add(new Task(m.getKey(), m.getValue()));
        }

        return listOfDistinctTask;
    }
}