package com.jeeva.QuizAPP.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jeeva.QuizAPP.Entity.qs;



@Repository
public interface QuestionDao extends JpaRepository<qs,Integer>  {
   List<qs> findByProgramminglanguage(String programminglanguage);

    @Query(value = "SELECT * FROM qs q WHERE q.programminglanguage = :programminglanguage ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<qs> findRandomQuestionsWithCategory(@Param("programminglanguage") String programminglanguage, @Param("limit") int limit);


}


