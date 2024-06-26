package com.example.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Domanda_Risposta;
import com.example.model.Quiz_Domanda;
@Repository
public interface Domanda_RispostaRepository extends JpaRepository<Domanda_Risposta, Long>{
	
	//Optional<Domanda_Risposta> findById(Long id);
	
	
	@Query("select d from Domanda_Risposta d Where d.id = :id") 
	Domanda_Risposta findByid(@Param("id") Long id);

}
