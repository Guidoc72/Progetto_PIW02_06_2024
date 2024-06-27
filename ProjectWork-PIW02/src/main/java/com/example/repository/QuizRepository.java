package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{
	
	
	@Query("select  q from Quiz q Where q.id = :id") 
	Quiz findByid(@Param("id") Long id);
	
	
	
	
	// List<Quiz_Domanda> findbyid(quiz.id);   --->  oppure(findbyid_quiz)
	
	
	
	// List quizzettone = List<Quiz_Domanda> findbyid_quiz(quiz.id);
	
	
	
	// while(x=10)
	//
	// List Domande;
	//List Domande .add (List<Domanda_risposta> findbyid(quizzettone(x).id));

	// x++;
}