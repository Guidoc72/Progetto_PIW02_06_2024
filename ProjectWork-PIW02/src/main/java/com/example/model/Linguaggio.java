package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "linguaggi")
public class Linguaggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome_argomento;
	
	
	// Getters e Setters
	public String getNome_argomento() {
		return nome_argomento;
	}
	public void setNome_argomento(String nome_argomento) {
		this.nome_argomento = nome_argomento;
	}
	
	
	
	
}
