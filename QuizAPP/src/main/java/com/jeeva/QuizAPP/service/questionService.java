package com.jeeva.QuizAPP.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jeeva.QuizAPP.Entity.qs;
import com.jeeva.QuizAPP.dao.QuestionDao;

@Service
public class questionService { 

        @Autowired
        QuestionDao questionDao;

    public ResponseEntity<List<qs>> getAllQuestions(){
        try{
        return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK) ;
        }catch(Exception exception){
        exception.printStackTrace();
        }return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<qs>> getQuestionByLanguage(String programminglanguage) {
        try{
        return new ResponseEntity<>(questionDao.findByProgramminglanguage(programminglanguage),HttpStatus.OK);
        }catch(Exception e ){
            e.printStackTrace();
        }return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(qs question) {
     questionDao.save(question);
      return new ResponseEntity<>("Succes,Question created Succesfully",HttpStatus.CREATED);

    }

    public void deleteQuestion(int id) {
               try {
            questionDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Question not found with id " + id);
        }

       
    }
}
