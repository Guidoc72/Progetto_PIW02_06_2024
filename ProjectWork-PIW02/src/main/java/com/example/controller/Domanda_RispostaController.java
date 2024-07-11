package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Domanda_Risposta;
import com.example.model.UserDetailsImpl;
import com.example.model.Utente;
import com.example.repository.RuoloRepository;
import com.example.repository.UtenteRepository;
import com.example.service.DomandaRispostaService;

import jakarta.validation.Valid;

@Controller
public class Domanda_RispostaController {
    
    @Autowired
    private DomandaRispostaService domandaRispostaService;
    
    @Autowired
    private UtenteRepository utenteRepository;
    
    @Autowired
    private RuoloRepository ruoloRepository;
    
    
    @GetMapping("/modificaDomande")
    public String listaDomande(Model model) {
    	
    	String mail = null;								/////////////////////////////////// INIZIO
		String ruolo = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean connesso = true;
		 if (authentication != null && authentication.isAuthenticated()) {							
			    Object principal = authentication.getPrincipal();
			    if (principal.equals("anonymousUser")) {	
			    	connesso = false;													/// METODO PER NAVBAR
			  } 	
			    if (principal instanceof UserDetails) {
				      UserDetails user = (UserDetails) principal;
				      UserDetailsImpl user2 = (UserDetailsImpl) user;
				       mail = user2.getUsername(); 
				       Utente utente = utenteRepository.findBymail(mail);
				       ruolo = ruoloRepository.findByid(utente.getRuolo());
				    } 
		 }
		
	model.addAttribute("connesso",connesso);
	model.addAttribute("ruolo",ruolo);					//////////////////////////////////// FINE
    	
        //System.err.println("SONO NEL GETMAPPING");
    	
        List<Domanda_Risposta> domandeRisposte = domandaRispostaService.getListaDomande();
        model.addAttribute("domandeRisposte", domandeRisposte);
        
        
        return "modificaDomande";
    }
    
    @PostMapping("/modificaDomanda")
    public String modificaDomanda(@Valid @ModelAttribute("domandeRisposte") Domanda_Risposta domanda, BindingResult result, Model model) {
        
    	String mail = null;								/////////////////////////////////// INIZIO
		String ruolo = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean connesso = true;
		 if (authentication != null && authentication.isAuthenticated()) {							
			    Object principal = authentication.getPrincipal();
			    if (principal.equals("anonymousUser")) {	
			    	connesso = false;													/// METODO PER NAVBAR
			  } 	
			    if (principal instanceof UserDetails) {
				      UserDetails user = (UserDetails) principal;
				      UserDetailsImpl user2 = (UserDetailsImpl) user;
				       mail = user2.getUsername(); 
				       Utente utente = utenteRepository.findBymail(mail);
				       ruolo = ruoloRepository.findByid(utente.getRuolo());
				    } 
		 }
		
	model.addAttribute("connesso",connesso);
	model.addAttribute("ruolo",ruolo);					//////////////////////////////////// FINE
    	
    	if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Errore nell'aggiornamento della domanda. Assicurati che tutti i campi siano completi.");
            List<Domanda_Risposta> domandeRisposte = domandaRispostaService.getListaDomande();
            model.addAttribute("domandeRisposte", domandeRisposte);
            return "modificaDomande";
        }

        boolean successo = domandaRispostaService.aggiornaDomanda(domanda);
        if (successo) {
            model.addAttribute("successMessage", "Domanda aggiornata con successo");
        } else {
            model.addAttribute("errorMessage", "Errore nell'aggiornamento della domanda. Assicurati che tutti i campi siano completi.");
        }
        List<Domanda_Risposta> domandeRisposte = domandaRispostaService.getListaDomande();
        model.addAttribute("domandeRisposte", domandeRisposte);
        return "modificaDomande";
    }
}
