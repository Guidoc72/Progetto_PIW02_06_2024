package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Domanda_Risposta;

@Repository
public interface Domanda_RispostaRepository extends JpaRepository<Domanda_Risposta, Long>{
	
	public Optional<Domanda_Risposta> findById (Long id_linguaggio);

}
