package com.example.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.thymeleaf.ITemplateEngine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.exception.MyException;
import com.example.model.Domanda_Risposta;
import com.example.model.DomandeRisposteWrapper;
import com.example.model.Linguaggio;
import com.example.model.Quiz;
import com.example.model.UserDetailsImpl;
import com.example.model.Utente;
import com.example.repository.QuizRepository;
import com.example.repository.RuoloRepository;
import com.example.repository.UtenteRepository;
import com.example.service.DomandaRispostaService;
import com.example.service.EmailService;
import com.example.service.LinguaggioService;
import com.example.service.StudenteService;
import com.example.service.UtenteService;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;


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
	
	@Autowired
	UtenteRepository utenteRepository;
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	RuoloRepository ruoloRepository;
	
	


	
	

	
	@GetMapping(path = {"/", "/home"})
	public String getProfile(Model model) {
       
		String mail = null;								/////////////////////////////////// INIZIO
		String ruolo = null;
		String nomeUtente = null;
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
				       nomeUtente = utente.getNome();
				    } 
		 }
		
	model.addAttribute("connesso",connesso);
	model.addAttribute("ruolo",ruolo);					//////////////////////////////////// FINE
	model.addAttribute("nomeUtente",nomeUtente);					//////////////////////////////////// FINE
		
	        return "home";
	        
	}
	
	
	
	@GetMapping("/recupera_password")
	public String getRecuperaPassword(Model model ) {
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
		
		return "recupera_password";
		
	}
	
	
	@PostMapping("/password_dimenticata")
    public  String processazioneRecuperoPassword(@RequestParam("mail") String mail) throws MessagingException {
		
		 

        if (utenteRepository.existsByMail(mail) == true) {
        	
        	SecureRandom random = new SecureRandom();
    	    byte[] bytes = new byte[32]; 
    	    random.nextBytes(bytes);											// METODO PER GENERARE UN TOKEN SICURO
    	    String token = Base64.getUrlEncoder().encodeToString(bytes);		// setto token nel db
    	    utenteRepository.updateTokenbymail(token, mail);
    	
    	    
   	  emailService.sendEmail(mail, "Recupero Password", "Per recuperare la password vai a questo link"+" http://localhost:8080/reset-password/"+token);  //invio email
    	    
   	 
            
            System.out.println("email inviata con succeso");
            return "/recupera_password";
        } else {
        	System.out.println("email non trovata");
            return "/recupera_password";
        }
    }
	
	
	 @GetMapping("/reset-password/{token}")
	    public String resetPassword(@PathVariable("token") String token, Model model) {
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
			
		 
		 
	       model.addAttribute("token",token); 
		 return"reset-password";
	    }
	 
	 

	 @PostMapping("/reset-password")
	    public String updatePassword(@RequestParam("token") String token,
	                                      @RequestParam("password") String password,
	                                      @RequestParam("password2") String password2) {
	    	
	    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    	if(password.equals(password2)) {
	    		
	    		Utente utente = utenteRepository.findBytoken(token);
	    		
	    		if(utente != null) {

	    			password=encoder.encode(password);
	    			utenteRepository.updatePasswordbymail(password, utente.getMail());	// cambio password
	    			SecureRandom random = new SecureRandom();
	        	    byte[] bytes = new byte[32]; 
	        	    random.nextBytes(bytes);											
	        	     token = Base64.getUrlEncoder().encodeToString(bytes);			//Cambio token nel db non resetto per eventuali problemi di security
	        	    utenteRepository.updateTokenbymail(token, utente.getMail());
	    			System.out.println("Password resettata con successo"); 
	    		}
	    		
	    	
	    		
	    	}else {
	    		System.out.println("Le password non coincidono");
	    	}
	        

	      
	    	
	    	return null;
	    }
	    
	
	
	@GetMapping("/registrazione")
	public String getRegistrazione(Model model ) {
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
		
		
		 

		 if (authentication != null && authentication.isAuthenticated()) {
			    // Utente autenticato
			    Object principal = authentication.getPrincipal();
			    													//----> METODO PER ESTRAPOLARE L'EMAIL DELL'UTENTE COLLEGATO
			    if (!principal.equals("anonymousUser")) {
			     
			    	return "home";
			  } 	
		 }
		
		
		Utente utente = new Utente();
		model.addAttribute("utente",utente);
		
		
		return "registrazione";
		
	}
	@PostMapping("/registrazioneUtente")
	public String getRegistrazioneCompletata(@ModelAttribute("utente") Utente utente,Model model,@RequestParam String password2) {
		
	
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println(utente);
		
		if(utente!=null) {
			
		
			if(utenteRepository.existsByMail(utente.getMail()) == false) {
				
				if(utente.getPassword_utente().equals(password2)) {
				
				utente.setPassword_utente(encoder.encode(utente.getPassword_utente()));
				utenteService.salvaUtenteRegistrazione(utente);
				System.out.println("Registrazione avvenuta con successo");
				}else {
					System.out.println("Password inserite sono diverse");
				}
				
			}else {
				System.out.println("Email gia registrata");
				}
		
			
		}
		
		
		
		return "login";
		
	}
	@GetMapping("/login")
	public String getLogin(Model model) {
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
		
		
		 

		 if (authentication != null && authentication.isAuthenticated()) {
			    // Utente autenticato
			    Object principal = authentication.getPrincipal();
			    													//----> METODO PER ESTRAPOLARE L'EMAIL DELL'UTENTE COLLEGATO
			    if (!principal.equals("anonymousUser")) {
			     
			    	return "home";
			  } 	
		 }
		
		
		return "login";
		
	}
	
	@GetMapping("/home_utente")
	public String getHomeUtente(Model model) {
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
		
	
		String username = null;
		

		  if (authentication != null && authentication.isAuthenticated()) {
		    // Utente autenticato
		    Object principal = authentication.getPrincipal();
		    													//----> METODO PER ESTRAPOLARE L'EMAIL DELL'UTENTE COLLEGATO
		    if (principal instanceof UserDetails) {
		      UserDetails user = (UserDetails) principal;
		      UserDetailsImpl user2 = (UserDetailsImpl) user;
		      System.out.println(user2);
		       username = user2.getUsername(); 
		      
		    } 
		  } 
		
		
		  Long idUtente = utenteRepository.trovaidBymail(username);
		  String nomeUtente = utenteRepository.trovaNomeBymail(username);
		  
		
		List<Quiz> quiz = new ArrayList<>() ;                                     // creo un contenitore per i possibili quiz
		List<Long> listaQuiz = studenteService.trovaQuizDisponibili(idUtente);   // creo una lista per con gli id di tutti i quiz disponibili
		
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
	     model.addAttribute("nomeUtente", nomeUtente);

		
		return "home_utente";
	}
	
	
	
	
	@PostMapping( "/visualizzazione_quiz")
	public String getQuiz(@ModelAttribute("quiz") Quiz quiz,Model model) {
		
	
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
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = null;
		  if (authentication != null && authentication.isAuthenticated()) {
		    // Utente autenticato
		    Object principal = authentication.getPrincipal();
		    													//----> METODO PER ESTRAPOLARE L'EMAIL DELL'UTENTE COLLEGATO
		    if (principal instanceof UserDetails) {
		      UserDetails user = (UserDetails) principal;
		      UserDetailsImpl user2 = (UserDetailsImpl) user;
		      System.out.println(user2);
		       username = user2.getUsername(); 
		      
		    } 
		  } 
		
		
		  Long idUtente = utenteRepository.trovaidBymail(username);
		
		studenteService.updateCompletato(idUtente, wrapper.getListaDomande1().get(0).getIdQuiz());       // modifica il quiz mettendo il completato  il primo campo è l'id dell'utente il secondo l'id quiz
		
		
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
	
	@ExceptionHandler(MyException.class)
    public String gestoreEccezioni(MyException ex) {
        return "errori.html";
    }

    @PostMapping(value = "/")
    public String handler() {
        throw new MyException("An error occurred!");
    }
    
    @GetMapping("/goToHomepage")
    public String goToHomepage(Model model) {
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
		
        return "home";
    }

	
	
}
