package com.jeeva.QuizAPP.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jeeva.QuizAPP.Entity.qs;
import com.jeeva.QuizAPP.service.questionService;

@RestController
@RequestMapping("question")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
   @Autowired
   questionService qservice;

    @GetMapping("allQuestions")
    @CrossOrigin(origins = "http://localhost:5500")
    public ResponseEntity< List<qs>> getAllQuestions(){
        return qservice.getAllQuestions();
    }

    @GetMapping("language/{programminglanguage}")
    @CrossOrigin(origins = "http://localhost:5500")
   public ResponseEntity<List<qs>> getQuestionBySpecificLanguage(@PathVariable String programminglanguage){
       return  qservice.getQuestionByLanguage(programminglanguage);
    }
    
    @PostMapping("add")
    @CrossOrigin(origins = "http://localhost:5500")
    public ResponseEntity<String> addQuestion(@RequestBody qs question){
        try {
            logger.info("Received question: {}", question);
            qservice.addQuestion(question);
            return new ResponseEntity<>("Question added successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error adding question", e);
            return ResponseEntity.status(500).body("Failed to add question: " + e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin(origins = "http://localhost:5500")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        try {
            qservice.deleteQuestion(id);
            return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting question", e);
            return ResponseEntity.status(500).body("Failed to delete question: " + e.getMessage());
        }
    }

}
