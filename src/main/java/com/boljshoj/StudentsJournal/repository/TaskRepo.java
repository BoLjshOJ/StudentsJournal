package com.boljshoj.StudentsJournal.repository;

import com.boljshoj.StudentsJournal.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Long> {
}