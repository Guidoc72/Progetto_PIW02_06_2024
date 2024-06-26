package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "domande_risposte")
public class Domanda_Risposta {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long id_linguaggio;
	
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
	
	@Transient
	private int risposta_selezionata;

	
public Domanda_Risposta() {}
public Domanda_Risposta(Long id_linguaggio, String domanda, String risposta_uno, String risposta_due,
			String risposta_tre, String risposta_quattro, int risposta_esatta) {
		
		setId_linguaggio(id_linguaggio);
		setDomanda (domanda);
		setRisposta_uno (risposta_uno);
		setRisposta_due (risposta_due);
		setRisposta_tre (risposta_tre);
		setRisposta_quattro (risposta_quattro);
		setRisposta_esatta (risposta_esatta);
	}
public Domanda_Risposta(Long id,Long id_linguaggio, String domanda, String risposta_uno, String risposta_due,
		String risposta_tre, String risposta_quattro, int risposta_esatta) {
	
	setId(id);
	setId_linguaggio(id_linguaggio);
	setDomanda (domanda);
	setRisposta_uno (risposta_uno);
	setRisposta_due (risposta_due);
	setRisposta_tre (risposta_tre);
	setRisposta_quattro (risposta_quattro);
	setRisposta_esatta (risposta_esatta);
}

public Domanda_Risposta(Long id, Long id_linguaggio, String domanda,int risposta_esatta, String risposta_uno, String risposta_due,    //lasciare o togliere id?
		String risposta_tre, String risposta_quattro,  int risposta_selezionata) {
	
	setId(id);
	setId_linguaggio(id_linguaggio);
	setDomanda (domanda);
	setRisposta_esatta (risposta_esatta);
	setRisposta_uno (risposta_uno);
	setRisposta_due (risposta_due);
	setRisposta_tre (risposta_tre);
	setRisposta_quattro (risposta_quattro);
	setRisposta_selezionata (risposta_selezionata);
}

	public Long getId_linguaggio() {
		return id_linguaggio;
	}

	public void setId_linguaggio(Long id_linguaggio) {
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
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getRisposta_selezionata() {
		return risposta_selezionata;
	}
	
	public void setRisposta_selezionata(int risposta_selezionata) {
		this.risposta_selezionata = risposta_selezionata;
	}
	
	@Override
	public String toString() {
		return "Domanda_Risposta [id=" + id + " id_linguaggio=" + id_linguaggio + ", domanda=" + domanda + ", risposta_uno="
				+ risposta_uno + ", risposta_due=" + risposta_due + ", risposta_tre=" + risposta_tre
				+ ", risposta_quattro=" + risposta_quattro + ", risposta_esatta=" + risposta_esatta + "risposta_selezionata=" + risposta_selezionata + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
