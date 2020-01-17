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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class MainController {
    private final String stackTrace = "[ERROR] Tests run: 4, Failures: 3, Errors: 1, Skipped: 0, Time elapsed: 0.401 s <<< FAILURE! - in com.ifmo.lesson6.LinkedListTest\n" +
            "[ERROR] shouldDoAddGetRemove Time elapsed: 0.272 s <<< FAILURE!\n" +
            "org.opentest4j.AssertionFailedError: Wrong item on index = 0 ==> expected: but was:\n" +
            "at org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:55)\n" +
            "at org.junit.jupiter.api.AssertionUtils.failNotEqual(AssertionUtils.java:62)\n" +
            "at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)\n" +
            "at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1135)\n" +
            "at com.ifmo.lesson6.assertions.AssertList.assertListValid(AssertList.java:23)";

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

    @GetMapping("/index/{student}")
    public String updateDescision(@PathVariable Student student){
        runCheckTests(student);
        studentsRepo.save(student);
        return "redirect:/index";
    }

    private void runCheckTests(@PathVariable Student student) {
        //TODO

        List<Task> tasks = taskRepo.findTasksByStudent(student);

        for (Task t : tasks){
            if (ThreadLocalRandom.current().nextInt(100) % 2 == 0){
                t.setResolved(true);
                t.setStackTrace("");
            } else {
                t.setStackTrace(stackTrace);
                t.setResolved(false);
            }
            taskRepo.save(t);
        }
        List<Task> errors = taskRepo.findTasksByStudentAndIsResolvedFalse(student);
        student.setFailedRunsOfTest(errors.size());
        student.setSuccessRunsOfTest(getDistinctListOfTasks().size() - errors.size());
        student.setLastCommitMessage(generateRandomString());
        student.setLastCommitTime(new Date());
        studentsRepo.save(student);
    }

    private String generateRandomString() {
       int leftLimit = 97;
       int rigthLimit = 122;
       int targetStringLength = 30;
       Random random = new Random();
       StringBuilder builder = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rigthLimit - leftLimit + 1));
            builder.append((char) randomLimitedInt);
        }
       return builder.toString();
    }

    public void updateTotalCountTask(){
        for (Student s : studentsRepo.findAll()){
            int totalTasks = taskRepo.findTasksByStudent(s).size();
            int successTasks = taskRepo.findTasksByStudentAndIsResolvedTrue(s).size();
            s.setTotalTest(totalTasks);
            s.setFailedRunsOfTest(totalTasks - successTasks);
            s.setSuccessRunsOfTest(successTasks);
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