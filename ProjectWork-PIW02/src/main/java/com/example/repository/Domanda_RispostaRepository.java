package com.example.repository;

import java.util.List;
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
	
	// Docente
    @Query("SELECT d FROM Domanda_Risposta d WHERE d.id = :id")
    List<Domanda_Risposta> trovaById(@Param("id") Long id);

    // Docente
    @Query("SELECT d FROM Domanda_Risposta d")
    List<Domanda_Risposta> trovaTutte();
	

	// Docente
 	@Query(value = "SELECT * FROM domande_risposte ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Domanda_Risposta> findRandomDomande();
	  
    
    // Docente
    @Query(value = "SELECT * FROM domande_risposte WHERE id_linguaggio = :linguaggioId ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Domanda_Risposta> findRandomDomandeByLinguaggioId(@Param("linguaggioId") Long linguaggioId);

    // Docente
    @Query("SELECT COUNT(d) FROM Domanda_Risposta d WHERE d.id_linguaggio = :id_linguaggio")
    long countByLinguaggioId(@Param("id_linguaggio") Long id_linguaggio);

    
    
}
