package com.jeeva.QuizAPP.service;

import com.jeeva.QuizAPP.Entity.QuestionWrapper;
import com.jeeva.QuizAPP.Entity.Quiz;
import com.jeeva.QuizAPP.Entity.Response;
import com.jeeva.QuizAPP.dao.QuestionDao;
import com.jeeva.QuizAPP.dao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.jeeva.QuizAPP.Entity.qs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
     QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createQuize(String category,int numQ,String title) {

        List<qs> questions=questionDao.findRandomQuestionsWithCategory(category,numQ);

        Quiz quiz=new Quiz();

        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Optional<Quiz> quiz =  quizDao.findById(id);
       List<qs> qsfromDb=quiz.get().getQuestions();
       List<QuestionWrapper> questionForUsers=new ArrayList<>();
       for(qs q: qsfromDb){
           QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestion(),q.option1,q.option2, q.option3, q.option4);
           questionForUsers.add(qw);
       }
       return new ResponseEntity<>(questionForUsers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Quiz quiz=quizDao.findById(id).get();
        List<qs> q=quiz.getQuestions();
        int right=0; int i=0;
        for(Response response: responses){
            if(response.getResponse().equals(q.get(i).getRightanswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
