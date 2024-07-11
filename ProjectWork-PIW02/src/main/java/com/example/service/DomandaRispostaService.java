package com.example.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Domanda_Risposta;
import com.example.model.Linguaggio;
import com.example.model.Quiz;
import com.example.model.Quiz_Domanda;
import com.example.repository.Domanda_RispostaRepository;
import com.example.repository.LinguaggioRepository;
import com.example.repository.Quiz_DomandaRepository;

import jakarta.transaction.Transactional;


@Service
public class DomandaRispostaService {
	
	@Autowired
	private Domanda_RispostaRepository domanda_rispostaRepository;
	
	@Autowired
	private Quiz_DomandaRepository quiz_DomandaRepository;
	
	
	@Autowired
	private LinguaggioRepository linguaggioRepository;

	
	
	
	// DOCENTE: metodo per salvare le domande e le risposte inserite
	public String salvaInserimentoDomandaRisposta (Domanda_Risposta formDomanda_Risposta){
		
		domanda_rispostaRepository.save(formDomanda_Risposta);
		
		return "Domande e risposte registrate con successo";
	}

	// STUDENTE
	public List<Domanda_Risposta> findDomanda(Quiz quiz) {
		
		
		List<Quiz_Domanda> quizDomanda = new ArrayList<>();
		
		quizDomanda = quiz_DomandaRepository.findByid_quiz(quiz.getId());	// Creazione Lista Quiz_Domande per avere una lista popolata da 10 id domande collegate a un id quiz
	
		
		List<Domanda_Risposta> domandeRisposte = new ArrayList<>();
		
	
		int x = 0;	
		while(x<10) {
			Quiz_Domanda quizDom=quizDomanda.get(x);
			domandeRisposte.add(domanda_rispostaRepository.findByid(quizDom.getId_domanda())); // query che estrapola l'oggetto Domanda_Risposta dall'array "quizDomanda"
			x++;
		}
	
		return domandeRisposte;
	}
	
	
	// Docente
	// Restituisci la lista di tutte le domande
    // Popolare idQuiz prima di restituire la lista
	public List<Domanda_Risposta> getListaDomande() {
	    return domanda_rispostaRepository.findAll();
//	    for (Domanda_Risposta domanda : domandeRisposte) {
//	        if (domanda.getId_linguaggio() != null) {
//	            Linguaggio linguaggio = linguaggioRepository.findById(domanda.getId_linguaggio()).orElse(null);
//	            if (linguaggio != null) {
//	                domanda.setNomeLinguaggio(linguaggio.getNomeArgomento());
//	            }
//	        }
//
//	        List<Long> idQuizList = quiz_DomandaRepository.findIdQuizByIdDomanda(domanda.getId());
//	        if (!idQuizList.isEmpty()) {
//	            domanda.setIdQuiz(idQuizList.get(0)); // Imposta solo il primo risultato se esiste
//	        }
//	    }
//	    return domandeRisposte;
	}

    // Docente
    // Metodo per aggiornare una domanda
    public boolean aggiornaDomanda(Domanda_Risposta domanda) {
        if (domanda.getId() == null || domanda.getDomanda() == null || domanda.getRisposta_uno() == null ||
            domanda.getRisposta_due() == null || domanda.getRisposta_tre() == null || domanda.getRisposta_quattro() == null) {
            return false;
        }

        // Recupera l'entità esistente per preservare l'ID del linguaggio
        Domanda_Risposta domandaEsistente = domanda_rispostaRepository.findById(domanda.getId()).orElse(null);
        if (domandaEsistente != null) {
            domanda.setId_linguaggio(domandaEsistente.getId_linguaggio());
            domanda.setLinguaggio(domandaEsistente.getLinguaggio());
        }

        domanda_rispostaRepository.save(domanda);
        return true;
    }
	

}
