package com.boljshoj.StudentsJournal.repository;

import com.boljshoj.StudentsJournal.domain.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentsRepo extends CrudRepository<Student, Long> {
    List<Student> findByFullName(String fullName);
}