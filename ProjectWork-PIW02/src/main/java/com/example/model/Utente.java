package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "utenti")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="il campo non può essere vuoto")
	private String ruolo;
	
	@NotEmpty(message="il campo non può essere vuoto")
	private String nome;
	
	@NotEmpty(message="il campo non può essere vuoto")
	private String cognome;
	
	@NotEmpty(message="il campo non può essere vuoto")
	@Email
	private String mail;
	
	@NotEmpty
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-])[a-zA-Z0-9!@#$%^&*()_+=-]{8,}$",message="la password deve essere lunga almeno 8 caratteri, contenere almeno una lettera maiuscola, un numero e un carattere speciale")
	private String password_utente;
	
	private boolean abilitato;
	
	public Utente() {}
	
	public Utente( @NotEmpty(message = "il campo non può essere vuoto") String ruolo,
			@NotEmpty(message = "il campo non può essere vuoto") String nome,
			@NotEmpty(message = "il campo non può essere vuoto") String cognome,
			@NotEmpty(message = "il campo non può essere vuoto") @Email String mail,
			@NotEmpty @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-])[a-zA-Z0-9!@#$%^&*()_+=-]{8,}$", message = "la password deve essere lunga almeno 8 caratteri, contenere almeno una lettera maiuscola, un numero e un carattere speciale") String password_utente,
			boolean abilitato) {

	
		setRuolo(ruolo);
		setNome(nome);
		setCognome(cognome);
		setMail(mail);
		setPassword_utente(password_utente);
		setAbilitato(abilitato);
	}

	public Utente(int id, @NotEmpty(message = "il campo non può essere vuoto") String ruolo,
			@NotEmpty(message = "il campo non può essere vuoto") String nome,
			@NotEmpty(message = "il campo non può essere vuoto") String cognome,
			@NotEmpty(message = "il campo non può essere vuoto") @Email String mail,
			@NotEmpty @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-])[a-zA-Z0-9!@#$%^&*()_+=-]{8,}$", message = "la password deve essere lunga almeno 8 caratteri, contenere almeno una lettera maiuscola, un numero e un carattere speciale") String password_utente,
			boolean abilitato) {

		setId(id);
		setRuolo(ruolo);
		setNome(nome);
		setCognome(cognome);
		setMail(mail);
		setPassword_utente(password_utente);
		setAbilitato(abilitato);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword_utente() {
		return password_utente;
	}

	public void setPassword_utente(String password_utente) {
		this.password_utente = password_utente;
	}

	public boolean isAbilitato() {
		return abilitato;
	}

	public void setAbilitato(boolean abilitato) {
		this.abilitato = abilitato;
	}
	

	@Override
	public String toString() {
		return "Utente [id=" + id + ", ruolo=" + ruolo + ", nome=" + nome + ", cognome=" + cognome + ", mail=" + mail
				+ ", password_utente=" + password_utente + ", abilitato=" + abilitato + "]";
	}
	
	
	
	

}
