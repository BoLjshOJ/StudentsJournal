package com.boljshoj.StudentsJournal.repository;

import com.boljshoj.StudentsJournal.domain.Student;
import com.boljshoj.StudentsJournal.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {
    List<Task> findTasksByStudent(Student student);

    List<Task> findTasksByStudentAndIsResolvedFalse (Student student);

    List<Task> findTasksByTaskName(String taskName);

    List<Task> findTasksByStudentAndIsResolvedTrue (Student student);
}