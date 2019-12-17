package com.boljshoj.StudentsJournal.repository;

import com.boljshoj.StudentsJournal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}