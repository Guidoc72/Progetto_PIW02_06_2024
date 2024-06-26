package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Domanda_Risposta;
import com.example.repository.Domanda_RispostaRepository;
import com.example.repository.LinguaggioRepository;

@Service
public class DomandaRispostaService {
	
	@Autowired
	private Domanda_RispostaRepository domanda_rispostaRepository;
		
	
	// metodo per salvare le domande e le risposte inserite
	public String salvaInserimentoDomandaRisposta (Domanda_Risposta formDomanda_Risposta){
		
		domanda_rispostaRepository.save(formDomanda_Risposta);
		
		return "Domande e risposte registrate con successo";
	}

}
