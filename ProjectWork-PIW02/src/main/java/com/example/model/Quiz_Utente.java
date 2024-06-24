package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="quiz_utenti")
public class Quiz_Utente {
	
	private Long id_quiz;
	private Long id_utenti;
	private boolean completato;
	
	
	public Quiz_Utente() {}
	
	
	public Quiz_Utente(Long id_quiz, Long id_utenti, boolean completato) {
		setId_quiz (id_quiz);
		setId_utenti (id_utenti);
		setCompletato (completato);
	}
	
	public Long getId_quiz() {
		return id_quiz;
	}



	public void setId_quiz(Long id_quiz) {
		this.id_quiz = id_quiz;
	}



	public Long getId_utenti() {
		return id_utenti;
	}



	public void setId_utenti(Long id_utenti) {
		this.id_utenti = id_utenti;
	}


	public boolean isCompletato() {
		return completato;
	}



	public void setCompletato(boolean completato) {
		this.completato = completato;
	}



	
	@Override
	public String toString() {
		return "Quiz_Utente [id_quiz=" + id_quiz + ", id_utenti=" + id_utenti + ", completato=" + completato + "]";
	}


	

}
