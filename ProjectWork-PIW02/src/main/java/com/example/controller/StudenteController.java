package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Domanda_Risposta;
import com.example.model.DomandeRisposteWrapper;
import com.example.model.Quiz;
import com.example.service.DomandaRispostaService;

@Controller
public class StudenteController {
	
	@Autowired
	DomandaRispostaService service;
	
	@GetMapping(path = {"/", "/visualizzazione_quiz.html"})
	public String getQuiz(Model model) {
		
		Quiz quiz = new Quiz();
		quiz.setId((long) 1);	//Faccio finta che mi viene passato il quiz... da aggiustare in futuro in base a come ci verrà passato dall'altro gruppo							
		quiz.setId_linguaggio((long) 1);
		
		
		
	 List<Domanda_Risposta>listaDomande1 =  service.findDomanda(quiz); //popolo la lista delle 10 domande attraverso le query del DB
     DomandeRisposteWrapper wrapper = new DomandeRisposteWrapper();
     wrapper.setListaDomande1(listaDomande1); // setto la lista nel wrapper
     model.addAttribute("wrapper", wrapper); // aggiungo il wrapper con all'interno al lista nel model
		

		return "visualizzazione_quiz";

}
	
	
	@PostMapping("/prova")
	public String getRisultato(@ModelAttribute("wrapper") DomandeRisposteWrapper wrapper, Model model) {
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
