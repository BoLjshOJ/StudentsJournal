package com.boljshoj.StudentsJournal.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String taskName;
    private String fileLocation;
    private boolean isResolved;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Task() {
    }

    public Task(String taskName, String fileLocation, Student student) {
        this.taskName = taskName;
        this.fileLocation = fileLocation;
        this.student = student;
    }

    public Task(String taskName, String fileLocation) {
        this.taskName = taskName;
        this.fileLocation = fileLocation;
    }
}