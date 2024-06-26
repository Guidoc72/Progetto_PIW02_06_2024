package com.example.model;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "domande_risposte")
public class Domanda_Risposta {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_linguaggio") // Questo mappa il campo `id_linguaggio` nella tabella `domande_risposte`
    private Linguaggio linguaggio;
	
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

	
    // Costruttori
    public Domanda_Risposta() {}
    
    public Domanda_Risposta(Linguaggio linguaggio, String domanda, String risposta_uno, String risposta_due, String risposta_tre, String risposta_quattro, int risposta_esatta) {
        this.linguaggio = linguaggio;
        this.domanda = domanda;
        this.risposta_uno = risposta_uno;
        this.risposta_due = risposta_due;
        this.risposta_tre = risposta_tre;
        this.risposta_quattro = risposta_quattro;
        this.risposta_esatta = risposta_esatta;
    }

    // Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Linguaggio getLinguaggio() {
	return linguaggio;
	}

	public void setLinguaggio(Linguaggio linguaggio) {
		this.linguaggio = linguaggio;
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
	
	@Override
	public String toString() {
		return "Domanda_Risposta [id=" + id + ", linguaggio=" + linguaggio + ", domanda=" + domanda + ", risposta_uno="
				+ risposta_uno + ", risposta_due=" + risposta_due + ", risposta_tre=" + risposta_tre
				+ ", risposta_quattro=" + risposta_quattro + ", risposta_esatta=" + risposta_esatta + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
