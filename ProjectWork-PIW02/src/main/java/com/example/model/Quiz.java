package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "quiz")
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotEmpty
	private Long id_linguaggio;

	public Quiz() {}
	
	public Quiz(@NotEmpty Long id_linguaggio) {
		
		setId_linguaggio (id_linguaggio);
	}
	public Quiz(Long id, @NotEmpty Long id_linguaggio) {
		
		setId(id);
		setId_linguaggio (id_linguaggio);
	}
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_linguaggio() {
		return id_linguaggio;
	}

	public void setId_linguaggio(Long id_linguaggio) {
		this.id_linguaggio = id_linguaggio;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", id_linguaggio=" + id_linguaggio + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
