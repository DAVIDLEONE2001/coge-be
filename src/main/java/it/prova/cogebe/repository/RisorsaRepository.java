package it.prova.cogebe.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.prova.cogebe.model.Risorsa;

public interface RisorsaRepository extends CrudRepository<Risorsa, Long> {
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO commessa_risorsa (risorsa_id, commessa_id) VALUES (?1, ?2)", nativeQuery = true)
    void linkRisorsaToCommessa(Long risorsaId, Long commessaId);
	
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM commessa_risorsa WHERE risorsa_id = :risorsaId")
	void deleteAllCommessaRisorsaByRisorsaId(@Param("risorsaId") Long risorsaId);



}
