package com.boljshoj.StudentsJournal.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private int successRunsOfTest;
    private int failedRunsOfTest;
    private String lastCommitMessage;
    private Date lastCommitTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User createdBy;

    public Student() {
    }

    public Student(String fullName, User user)
    {
        this.createdBy = user;
        this.fullName = fullName;
    }
}