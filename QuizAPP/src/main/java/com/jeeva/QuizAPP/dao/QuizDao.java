package com.jeeva.QuizAPP.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeeva.QuizAPP.Entity.Quiz;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {
    
}
