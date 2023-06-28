package it.prova.cogebe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.cogebe.model.Azienda;

public interface AziendaRepository extends CrudRepository<Azienda, Long> {

	@Query(value = "select a.* from azienda a\r\n" + "inner join azienda_commesse ac on a.id=ac.azienda_id\r\n"
			+ "inner join commessa c on ac.commesse_id=c.id\r\n"
			+ "group by (a.id) order by sum(c.importo) desc", nativeQuery = true)
	public List<Azienda> findAziendeConCostoDesc();

}
