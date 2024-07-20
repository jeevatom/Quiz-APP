package com.jeeva.QuizAPP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeeva.QuizAPP.Entity.qs;
import com.jeeva.QuizAPP.service.questionService;

@RestController
@RequestMapping("question")
public class QuestionController {

   @Autowired
   questionService qservice;

    @GetMapping("allQuestions")
    public ResponseEntity< List<qs>> getAllQuestions(){
        return qservice.getAllQuestions();
    }

    @GetMapping("language/{programminglanguage}")
   public ResponseEntity<List<qs>> getQuestionBySpecificLanguage(@PathVariable String programminglanguage){
       return  qservice.getQuestionByLanguage(programminglanguage);
    }
    
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody qs question){
   return  qservice.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public void deleteQuestion(@RequestBody int id){
       qservice.deleteQuestion(id);
    }

}
