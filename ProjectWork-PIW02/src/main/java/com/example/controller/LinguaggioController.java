package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Linguaggio;
import com.example.service.LinguaggioService;

@Controller
public class LinguaggioController {

	@Autowired
	private LinguaggioService linguaggioService;
	
	@GetMapping("/inserimentoLinguaggio")
	public String mostraInserimentoLinguaggio(Model model) {
		
		model.addAttribute("linguaggi", new Linguaggio());
		System.out.println("Sono nel GET di inserimentoLinguaggio");
		
		return "inserimentoLinguaggio";
	}
	
	@PostMapping("/inserimentoLinguaggio")
	public String inserimentoLinguaggio(@RequestParam("nomeArgomento") String nomeArgomento,
            Model model) {
		
		// Creo nuovo oggetto Linguaggio()
		Linguaggio nuovoLinguaggio = new Linguaggio();
		System.out.println("Creo nuovo oggetto Linguaggio");
		
		// Asssegno il @RequestParam("nome_argomento") String nome_argomento al nuovo oggetto nuovoLinguaggio
		nuovoLinguaggio.setNomeArgomento(nomeArgomento);
		System.out.println("Linguaggio inserito: " + nuovoLinguaggio);
		
		// Salvo il nuovo linguaggio
		linguaggioService.salvaLinguaggio(nuovoLinguaggio);
		System.out.println("Linguaggio salvato");
		
		return "redirect:/LandingPageDocente";
	}
	
	
}
