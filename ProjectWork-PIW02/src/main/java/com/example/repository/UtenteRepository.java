package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Utente;

import jakarta.transaction.Transactional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    // Metodo per trovare un utente tramite l'indirizzo email
    Utente findByMail(String mail);

    // Query customizzata per aggiornare i campi di un utente specifico
    @Modifying
    @Transactional
    @Query("update Utente u set u.nome = :nome, u.cognome = :cognome, u.mail = :mail, u.ruolo = :ruolo where u.id = :id_utente")
    void updateUtente(@Param("id_utente") Long id_utente, @Param("nome") String nome, @Param("cognome") String cognome, @Param("mail") String mail, @Param("ruolo") Long ruolo);

    // salva oggetto Utente Optional
	void save(Optional<Utente> utente);

}