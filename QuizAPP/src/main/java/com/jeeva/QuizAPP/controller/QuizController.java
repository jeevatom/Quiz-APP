package com.jeeva.QuizAPP.controller;

import com.jeeva.QuizAPP.Entity.QuestionWrapper;
import com.jeeva.QuizAPP.Entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jeeva.QuizAPP.Entity.qs;
import com.jeeva.QuizAPP.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
   
    @Autowired
    QuizService quizservice;
    
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
         return quizservice.createQuize(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuents(@PathVariable Integer id){
        return quizservice.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizservice.calculateResult(id,responses);
    }
}
