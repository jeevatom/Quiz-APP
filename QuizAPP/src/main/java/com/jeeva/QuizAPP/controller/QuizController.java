package com.jeeva.QuizAPP.controller;

import com.jeeva.QuizAPP.Entity.QuestionWrapper;
import com.jeeva.QuizAPP.Entity.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jeeva.QuizAPP.Entity.qs;
import com.jeeva.QuizAPP.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class QuizController {
   
    @Autowired
    QuizService quizservice;
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
    @PostMapping("create")
    @CrossOrigin(origins = "http://localhost:5500")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
         return quizservice.createQuize(category,numQ,title);
    }

    @GetMapping("get/{id}")
    @CrossOrigin(origins = "http://localhost:5500")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuents(@PathVariable Integer id){
        return quizservice.getQuizQuestions(id);
    }

    @GetMapping("getByLanguage/{language}")
    @CrossOrigin(origins = "http://localhost:5500")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByLanguage(@PathVariable String language) {
        return quizservice.getQuizQuestionsByLanguage(language);
    }
    @DeleteMapping("deleteQuiz/{language}")
    @CrossOrigin(origins = "http://localhost:5500")
    public ResponseEntity<String> deleteByQuestion(@PathVariable String language){
        return quizservice.deleteQuiz(language);
    }
    @PostMapping("submit/{id}")
    @CrossOrigin(origins = "http://localhost:5500")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizservice.calculateResult(id,responses);
    }
}
