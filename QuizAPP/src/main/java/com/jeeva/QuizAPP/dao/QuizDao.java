package com.jeeva.QuizAPP.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeeva.QuizAPP.Entity.Quiz;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {
    List<Quiz> findByLanguage(String language);
}
