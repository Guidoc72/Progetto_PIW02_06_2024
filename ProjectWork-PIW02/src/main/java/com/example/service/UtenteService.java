package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Utente;
import com.example.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository utenteRepository;
	
	// Metodo per recuperare tutti gli utenti
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }
	
	
}
