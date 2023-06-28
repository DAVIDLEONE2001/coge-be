package it.prova.cogebe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.cogebe.model.Rapportino;

public interface RapportinoRepository extends CrudRepository<Rapportino, Long> {

	@Query("from Rapportino r left join fetch r.risorsa a")
	List<Rapportino> getAllEagerWithRisorsa();

	@Query("from Rapportino r  join fetch r.risorsa a left join fetch r.commessa c where r.id =?1")
	Rapportino getSingleEager(Long id);
}
