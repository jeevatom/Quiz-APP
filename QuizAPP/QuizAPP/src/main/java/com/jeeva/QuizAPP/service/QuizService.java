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
import org.springframework.web.bind.annotation.PathVariable;

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
        quiz.setLanguage(category);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Optional<Quiz> quiz =  quizDao.findById(id);
       List<qs> qsfromDb=quiz.get().getQuestions();
       List<QuestionWrapper> questionForUsers=new ArrayList<>();
       for(qs q: qsfromDb){
           QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(), q.getOption3(),q.getOption4(),q.getRightanswer());
           questionForUsers.add(qw);
       }
       return new ResponseEntity<>(questionForUsers,HttpStatus.OK);
    }

//    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByLanguage(String language) {
//        List <Quiz> quizzes = quizDao.findByLanguage(language);
//        List<qs> qsfromDb=quizzes.get().getQuestions();
//        List<QuestionWrapper> questionForUsers=new ArrayList<>();
//        for(qs q: qsfromDb){
//            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(), q.getOption3(),q.getOption4(),q.getRightanswer());
//            questionForUsers.add(qw);
//        }
//        return new ResponseEntity<>(questionForUsers,HttpStatus.OK);
//    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByLanguage(String language) {
        List<Quiz> quizzes = quizDao.findByLanguage(language);
        List<QuestionWrapper> questionForUsers = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            List<qs> qsfromDb = quiz.getQuestions();
            for (qs q : qsfromDb) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getRightanswer());
                questionForUsers.add(qw);
            }
        }

        if (questionForUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(questionForUsers, HttpStatus.OK);
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

    public ResponseEntity<String> deleteQuiz(String language) {
        List<Quiz> quizzes = quizDao.findByLanguage(language);

        if (quizzes.isEmpty()) {
            return new ResponseEntity<>("No quizzes found for the specified language.", HttpStatus.NOT_FOUND);
        }
        quizDao.deleteAll(quizzes);
        return new ResponseEntity<>("Quizzes deleted successfully.", HttpStatus.OK);
    }
}
