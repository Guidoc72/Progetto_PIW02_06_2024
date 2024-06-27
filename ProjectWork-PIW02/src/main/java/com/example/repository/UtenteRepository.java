package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {


}
