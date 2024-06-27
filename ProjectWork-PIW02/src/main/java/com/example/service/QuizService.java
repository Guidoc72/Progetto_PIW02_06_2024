package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Quiz;
import com.example.model.Utente;
import com.example.repository.QuizRepository;
import com.example.repository.Quiz_UtenteRepository;
import com.example.repository.UtenteRepository;

@Service
public class QuizService {
	
	@Autowired
	private QuizRepository quizRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
    private Quiz_UtenteRepository quiz_UtenteRepository;
	
	// Metodo per recuperare tutti i linguaggi
    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }
    
    // Metodo per recuperare un linguaggio per ID
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id); 
    }
    
    // Metodo per recuperare tutti gli utenti
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }
    
    
    // Metodo per assegnare il quiz ad uno studente
    
    

}
