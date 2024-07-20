package com.jeeva.QuizAPP.Entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;


import java.util.List;

@Entity
@Table(name = "quiz_table")
@Data
public class Quiz {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<qs> getQuestions() {
        return questions;
    }

    public void setQuestions(List<qs> questions) {
        this.questions = questions;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    private String title;

   @ManyToAny
   public List<qs> questions;

    
}
