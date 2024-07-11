package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Linguaggio;
import com.example.repository.Domanda_RispostaRepository;
import com.example.repository.LinguaggioRepository;

@Service
public class LinguaggioService {
	
	@Autowired
	private LinguaggioRepository linguaggioRepository;
	
	@Autowired
	private Domanda_RispostaRepository domandaRispostaRepository;
	
	// metodo per salvare il linguaggio inserito/selezionato
	public boolean salvaLinguaggio (Linguaggio linguaggio) {
		
		// Se il linguaggio esiste -> restituisce false
		if (linguaggioRepository.existsByNomeArgomentoIgnoreCase(linguaggio.getNomeArgomento())) {
			System.out.println("LINGUAGGIO ESISTENTE NEL DB");
			return false;
		}
		// altrimenti salva il linguaggio
		linguaggioRepository.save(linguaggio);
		System.out.println("LINGUAGGIO SALVATO: sono nel metodo salvaLinguaggio in LinguaggioService.java");
		return true;
	}
	
	// Metodo per recuperare tutti i linguaggi
    public List<Linguaggio> getAllLinguaggi() {    	
        return linguaggioRepository.findAll();
    }
    
    // Metodo per recuperare un linguaggio per ID
    public Optional<Linguaggio> getLinguaggioById(Long id) {
        return linguaggioRepository.findById(id); 
    }
    public boolean existsByNomeArgomentoIgnoreCase(String nomeArgomento) {
        return linguaggioRepository.existsByNomeArgomentoIgnoreCase(nomeArgomento);
    }
    

	


}
