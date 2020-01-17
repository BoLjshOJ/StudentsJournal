package com.boljshoj.StudentsJournal.controllers;

import com.boljshoj.StudentsJournal.domain.Student;
import com.boljshoj.StudentsJournal.domain.Task;
import com.boljshoj.StudentsJournal.repository.StudentsRepo;
import com.boljshoj.StudentsJournal.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tasks")
public class TaskContoller {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private StudentsRepo studentsRepo;

    @GetMapping
    private String tasksList(Model model){
        model.addAttribute("tasks", getDistinctListOfTasks());
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

        model.addAttribute("tasks", getDistinctListOfTasks());
        return "tasks";
    }

    @GetMapping("{taskName}")
    public String removeTask(
            @PathVariable String taskName,
            Model model
    ){
        List<Task> tasksByTaskName = taskRepo.findTasksByTaskName(taskName);
        taskRepo.deleteAll(tasksByTaskName);
        model.addAttribute("tasks", getDistinctListOfTasks());
        return "tasks";
    }

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