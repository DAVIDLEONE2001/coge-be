package it.prova.cogebe.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.cogebe.model.Azienda;

public interface AziendaRepository extends CrudRepository<Azienda, Long> , CustomAziendaRepository {
			
	
	
}
