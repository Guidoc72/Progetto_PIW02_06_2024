package com.example.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Domanda_Risposta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface Domanda_RispostaRepository extends JpaRepository<Domanda_Risposta, Long>{
	
	// Docente
	public Optional<Domanda_Risposta> findById (Long id_linguaggio);	
	
	
	// Studente
	@Query("select d from Domanda_Risposta d Where d.id = :id") 
	public Domanda_Risposta findByid(@Param("id") Long id);

}
