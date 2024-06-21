package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "domande_risposte")
public class Domanda_Risposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_linguaggio;
	
	@Column(length = 500)
	private String domanda;
	@Column(length = 200)
	private String risposta_uno;
	@Column(length = 200)
	private String risposta_due;
	@Column(length = 200)
	private String risposta_tre;
	@Column(length = 200)
	private String risposta_quattro;
	@Column
	private int risposta_esatta;

	
	
public Domanda_Risposta(int id_linguaggio, String domanda, String risposta_uno, String risposta_due,
			String risposta_tre, String risposta_quattro, int risposta_esatta) {
		
		setId_linguaggio(id_linguaggio);
		setDomanda (domanda);
		setRisposta_uno (risposta_uno);
		setRisposta_due (risposta_due);
		setRisposta_tre (risposta_tre);
		setRisposta_quattro (risposta_quattro);
		setRisposta_esatta (risposta_esatta);
	}

	public int getId_linguaggio() {
		return id_linguaggio;
	}

	public void setId_linguaggio(int id_linguaggio) {
		this.id_linguaggio = id_linguaggio;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public String getRisposta_uno() {
		return risposta_uno;
	}

	public void setRisposta_uno(String risposta_uno) {
		this.risposta_uno = risposta_uno;
	}

	public String getRisposta_due() {
		return risposta_due;
	}

	public void setRisposta_due(String risposta_due) {
		this.risposta_due = risposta_due;
	}

	public String getRisposta_tre() {
		return risposta_tre;
	}

	public void setRisposta_tre(String risposta_tre) {
		this.risposta_tre = risposta_tre;
	}

	public String getRisposta_quattro() {
		return risposta_quattro;
	}

	public void setRisposta_quattro(String risposta_quattro) {
		this.risposta_quattro = risposta_quattro;
	}

	public int getRisposta_esatta() {
		return risposta_esatta;
	}

	public void setRisposta_esatta(int risposta_esatta) {
		this.risposta_esatta = risposta_esatta;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
