package com.jeeva.QuizAPP.Entity;

import lombok.Data;

@Data
public class QuestionWrapper {



    private Integer id;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String question;
    public QuestionWrapper(Integer id, String option1, String option2, String option3, String option4, String question) {
        this.id = id;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.question = question;
    }
}
