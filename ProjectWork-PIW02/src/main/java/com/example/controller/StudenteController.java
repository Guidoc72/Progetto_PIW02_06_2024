package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Domanda_Risposta;
import com.example.model.DomandeRisposteWrapper;
import com.example.model.Linguaggio;
import com.example.model.Quiz;
import com.example.repository.QuizRepository;
import com.example.service.DomandaRispostaService;
import com.example.service.LinguaggioService;
import com.example.service.StudenteService;

@Controller
public class StudenteController {
	
	@Autowired
	DomandaRispostaService service;
	
	@Autowired
	LinguaggioService linguaggioService;
	
	@Autowired
	StudenteService studenteService;
	
	@Autowired
	QuizRepository quizRepository;
	
	
	@GetMapping("/home_utente.html")
	public String getHomeUtente(Model model) {
		
		List<Quiz> quiz = new ArrayList<>() ;                                     // creo un contenitore per i possibili quiz
		List<Long> listaQuiz = studenteService.trovaQuizDisponibili((long) 2);   // creo una lista per con gli id di tutti i quiz disponibili
		
		int numeroQuiz=listaQuiz.size();
		
		if(listaQuiz!=null) {		// controlla che la lista esista
			
			for(Long lista : listaQuiz) {
				
				Quiz quizAppoggio = quizRepository.findByid(lista);
				Optional<Linguaggio> linguaggio = linguaggioService.getLinguaggioById(quizAppoggio.getId());
				quizAppoggio.setNomeLinguaggio(linguaggio.get().getNomeArgomento());
				quiz.add(quizAppoggio);				// popolo la lista di quiz in base ai quiz che ho
				
				
			}
		}
		
	     model.addAttribute("quiz", quiz);
	     model.addAttribute("numeroQuiz", numeroQuiz);

		
		return "home_utente";
	}
	
	
	
	@PostMapping( "/visualizzazione_quiz.html")
	public String getQuiz(@ModelAttribute("quiz") Quiz quiz,Model model) {
		
		System.out.println("BBBBBBBBB--->" + quiz);
//		quiz.setId((long) 1);	//Faccio finta che mi viene passato il quiz... da aggiustare in futuro in base a come ci verrà passato dall'altro gruppo							
//		quiz.setId_linguaggio((long) 1);
		
		
		long idQuiz=quiz.getId();
		
		Optional<Linguaggio> linguaggio = linguaggioService.getLinguaggioById(quiz.getId());
		String nomeLinguaggio= linguaggio.get().getNomeArgomento();// estrapolo il nome dell'argomento del quiz
		
		List<Domanda_Risposta>listaDomande1 =  service.findDomanda(quiz); //popolo la lista delle 10 domande attraverso le query del DB
		DomandeRisposteWrapper wrapper = new DomandeRisposteWrapper();
		 
		wrapper.setListaDomande1(listaDomande1); // setto la lista nel wrapper
		wrapper.getListaDomande1().get(0).setIdQuiz(idQuiz);
		
		model.addAttribute("wrapper", wrapper); // aggiungo il wrapper con all'interno al lista nel model
		model.addAttribute("nomeLinguaggio", nomeLinguaggio);


		return "visualizzazione_quiz";
		

}
	
	
	@PostMapping("/completato")
	public String getRisultato(@ModelAttribute("wrapper") DomandeRisposteWrapper wrapper,  Model model) {
		
		studenteService.updateCompletato(2L, wrapper.getListaDomande1().get(0).getIdQuiz());       // modifica il quiz mettendo il completato  il primo campo è l'id dell'utente il secondo l'id quiz
		
		
		Optional<Linguaggio> linguaggio = linguaggioService.getLinguaggioById(wrapper.getListaDomande1().get(0).getId_linguaggio());
		 String nomeLinguaggio= linguaggio.get().getNomeArgomento();						// estrapolo il nome dell'argomento del quiz
		
		System.out.println("nome Linguaggio"+nomeLinguaggio);
        List<Domanda_Risposta> listaDomande1 = wrapper.getListaDomande1();
        int punteggio = 0;

        if (listaDomande1 != null) {
            for (Domanda_Risposta dr : listaDomande1) {
            	 int rispostaSelezionataInt = dr.getRisposta_selezionata();
                 int rispostaEsattaInt = dr.getRisposta_esatta();
                 if (rispostaSelezionataInt == rispostaEsattaInt) {
                     punteggio++;
                 }
            }
        } else {
            System.out.println("Lista domande è null");
        }
        
        model.addAttribute("wrapper", wrapper);
        model.addAttribute("punteggio", punteggio); // Passa il punteggio al modello
        model.addAttribute("nomeLinguaggio", nomeLinguaggio);


        return "risultato_quiz";
		
	}
	
	
	
	//int x = 0;
	// int punteggio;
	// while(x<10){
	//if(listaDomande.get(x).getRisposta_esatta == listaDomande.get(x).getRisposta_selezionata{
	//punteggio += 1;
	//}
	//x++;
	// }
	
	
}
