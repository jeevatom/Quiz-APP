package com.jeeva.QuizAPP.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "qs")
public class qs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String programminglanguage;
    public String difficultylevel;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String question;
    public String rightanswer;
   
   
}
