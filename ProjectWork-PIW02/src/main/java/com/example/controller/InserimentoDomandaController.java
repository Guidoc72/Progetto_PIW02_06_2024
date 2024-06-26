package com.example.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Domanda_Risposta;
import com.example.model.Linguaggio;
import com.example.service.DomandaRispostaService;
import com.example.service.LinguaggioService;



@Controller
public class InserimentoDomandaController {
	
	
	@Autowired
    private LinguaggioService linguaggioService;
	
	@Autowired
	private DomandaRispostaService domandaRispostaService;
	

	// Mostra il form di registrazione
    @GetMapping("/inserimentoDomanda")
    public String mostraFormInserimentoDomande(Model model) {
    	
    	
        model.addAttribute("formDomande", new Domanda_Risposta());
        model.addAttribute("linguaggi", linguaggioService.getAllLinguaggi());
        
        System.out.println("Sono nel controller GET");
                       
               
        return "inserimentoDomanda";
    }
    
    @PostMapping("/inserimentoDomanda")
    public String inserimentoDomande(
    		@RequestParam("linguaggio.id") Long linguaggioId,
            @RequestParam("domanda") String domanda,
            @RequestParam("risposta_uno") String risposta_uno,
            @RequestParam("risposta_due") String risposta_due,
            @RequestParam("risposta_tre") String risposta_tre,
            @RequestParam("risposta_quattro") String risposta_quattro,
            @RequestParam("risposta_esatta") int risposta_esatta,
            Model model) {

    	
        // Recupera il linguaggio selezionato dal database
      Optional<Linguaggio> linguaggio = linguaggioService.getLinguaggioById(linguaggioId);
      if (!linguaggio.isPresent()) {
          // Gestione errore se il linguaggio non esiste
          System.out.println("Linguaggio non trovato!");
          return "errorPage"; // O un'altra pagina di errore
      }
    	
      
      
        System.out.println("Creo l'oggetto nuovaDomanda");
        Domanda_Risposta nuovaDomanda = new Domanda_Risposta();
        System.out.println("Oggetto nuovaDomanda creato");



        nuovaDomanda.setLinguaggio(linguaggio.get());
        nuovaDomanda.setDomanda(domanda);
        nuovaDomanda.setRisposta_uno(risposta_uno);
        nuovaDomanda.setRisposta_due(risposta_due);
        nuovaDomanda.setRisposta_tre(risposta_tre);
        nuovaDomanda.setRisposta_quattro(risposta_quattro);
        nuovaDomanda.setRisposta_esatta(risposta_esatta);

        domandaRispostaService.salvaInserimentoDomandaRisposta(nuovaDomanda);

        System.out.println("--------------------------------");
        System.out.println("Linguaggio selezionato nel form: " + linguaggio.get());
        System.out.println("--------------------------------");
        System.out.println("Domande inserite nel form: " + nuovaDomanda);

        return "redirect:/LandingPageDocente";
    }


}
