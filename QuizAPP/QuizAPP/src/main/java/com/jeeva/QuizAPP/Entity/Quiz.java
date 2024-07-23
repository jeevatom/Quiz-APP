package com.jeeva.QuizAPP.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ManyToAny;

import lombok.Data;


import java.util.List;

@Entity
@Table(name = "quiz_table")
@Data
public class Quiz {

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<qs> getQuestions() {
        return questions;
    }

    public void setQuestions(List<qs> questions) {
        this.questions = questions;
    }

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    private String title;

    private String language;

   @ManyToAny
   public List<qs> questions;

    
}
