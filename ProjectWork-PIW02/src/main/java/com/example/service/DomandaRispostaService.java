package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Domanda_Risposta;
import com.example.model.Quiz;
import com.example.model.Quiz_Domanda;
import com.example.repository.Domanda_RispostaRepository;
import com.example.repository.Quiz_DomandaRepository;

@Service
public class DomandaRispostaService {
	
	
@Autowired
public Quiz_DomandaRepository quiz_DomandaRepository;
@Autowired
public Domanda_RispostaRepository domanda_RispostaRepository;
	
	


	public List<Domanda_Risposta> findDomanda(Quiz quiz) {
		
		
		
		List<Quiz_Domanda> quizDomanda = new ArrayList<>();
		
		quizDomanda = quiz_DomandaRepository.findByid_quiz(quiz.getId());	// Creazione Lista Quiz_Domande per avere una lista popolata da 10 id domande collegate a un id quiz
		System.out.println(quizDomanda);
	
		
		List<Domanda_Risposta> domandeRisposte = new ArrayList<>();
		
	
	int x = 0;	
	while(x<10) {
		Quiz_Domanda quizDom=quizDomanda.get(x);
		domandeRisposte.add(domanda_RispostaRepository.findByid(quizDom.getId_domanda())); // query che estrapola l'oggetto Domanda_Risposta dall'array "quizDomanda"
		System.out.println(domandeRisposte);
		x++;
	}
	
		return domandeRisposte;
	}
	
	

}
