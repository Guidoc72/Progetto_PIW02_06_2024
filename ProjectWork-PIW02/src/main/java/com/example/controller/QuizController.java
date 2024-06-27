package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.LinguaggioService;
import com.example.service.QuizService;
import com.example.service.UtenteService;

@Controller
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	
	@Autowired	
	private UtenteService utenteService;
	
	@GetMapping("/assegnazioneQuizStudente")
	public String mostraAssegnazioneQuizStudente(Model model) {
		
		model.addAttribute("quiz", quizService.getAllQuiz()); // come linguaggi
		model.addAttribute("studenti", utenteService.getAllUtenti());
		
		return "assegnazioneQuizStudente";
	}
	
	@PostMapping("/assegnazioneQuizStudente")
	public String assegnazioneQuizStudente(
			@RequestParam("quiz.id") Long quizId,
			@RequestParam("utente.id") Long utenteId,
			Model model
			) {
		
		 
		
		return "redirect:/landingPageDocente";
	}

}
