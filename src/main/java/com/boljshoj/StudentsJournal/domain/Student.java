package com.boljshoj.StudentsJournal.domain;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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
    private int totalTest;
    private String lastCommitMessage;
    private Date lastCommitTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User createdBy;

    private String groupName;

    public Student() {
    }

    public Student(String fullName, User user, String group)
    {
        this.fullName = fullName;
        this.createdBy = user;
        this.groupName = group;
    }

    public String countSuccessPercent(){
        double countSuccessPercent = 0.0;
        if (getTotalTest() != 0) {
            countSuccessPercent = ((double) successRunsOfTest / (double) getTotalTest()) * 100.0;
        }
        return String.format("%.0f", countSuccessPercent);
    }

    public String getStringFromCommitDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        if (getLastCommitTime() == null){
            return "";
        } else {
            return formatter.format(getLastCommitTime());
        }
    }
}