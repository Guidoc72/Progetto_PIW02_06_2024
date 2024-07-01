package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.model.Utente;
import com.example.service.UtenteService;

@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    // richiesta GET a "/modificaStudente"
    @GetMapping("/modificaStudente")
    public String getAllUtenti(Model model) {
        // Aggiunge un nuovo oggetto Utente al modello per il form di inserimento/modifica
        model.addAttribute("Studenti", new Utente());
        // Recupera tutti gli utenti dal servizio e li aggiunge al modello per la visualizzazione
        model.addAttribute("Utente", utenteService.getAllUtenti());
        // Ritorna il nome della vista da visualizzare (modificaStudente.html in questo caso)
        return "modificaStudente";
    }

    //  richiesta POST a "/modificaStudente" per aggiornare un utente
    @PostMapping("/modificaStudente")
    public String updateUtente(
            @RequestParam("id") Long id,
            @RequestParam("nome") String nome,
            @RequestParam("cognome") String cognome,
            @RequestParam("mail") String mail,
            @RequestParam("ruolo") Long ruolo,
            Model model) {

    	System.out.println("DATI PROVENTINTI DAL GETMAPPING -> " + id + nome + cognome + mail + ruolo);
    	
    	System.out.println("-------- SONO NEL POST MAPPING ---------");
    	
        // Verifica che i parametri non siano vuoti prima di procedere
        if (nome.isEmpty() || cognome.isEmpty() || mail.isEmpty()) {
        	
        	System.out.println("------- CONTROLLO SE I CAMPI SONO VUOTI ---------");
        	
            // Se uno dei parametri è vuoto, aggiunge un messaggio di errore al modello
            model.addAttribute("error", "Tutti i campi sono obbligatori.");
            
            System.err.println("-------- ERRORE CAMPI VUOTI ---------");
            
            // Ritorna alla pagina di modifica con il messaggio di errore
            return "modificaStudente";
        }

        System.err.println("------ SONO FUORI IL TRY / CATCH -------");
        // Chiamata al servizio per aggiornare l'utente nel database
        try {
        	System.out.println("------- TRY -> PROVO AD AGGIORNARE I DATI -------");
            utenteService.updateUtente(id, nome, cognome, mail, ruolo);
            // Se l'aggiornamento va a buon fine, reindirizza alla pagina principale per il docente
            
            // Stampa dati utente aggiornati
            System.out.println("UTENTE AGGIORNATO CON SUCCESSO: \n" + "[ id: " + id + " ]" + "[ nome: " + nome + " ]" + "[ cognome: " + cognome + " ]" + "[ mail: " + mail + "]" + "[ ruolo: " + ruolo + "]");
            
            return "redirect:/landingPageDocente";
        } catch (Exception e) {
        	
        	System.out.println("------ CATCH -> SONO NEL CATCH: ERRORE");
        	
            // Se si verifica un'eccezione durante l'aggiornamento, gestisce l'errore
            model.addAttribute("error", "Si è verificato un errore durante l'aggiornamento dell'utente.");
            // Ritorna alla pagina di modifica con un messaggio di errore
            return "modificaStudente";
        }
    }
}
    


