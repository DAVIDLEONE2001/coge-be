package it.prova.cogebe.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import it.prova.cogebe.model.Commessa;

public interface CommessaRepository extends CrudRepository<Commessa, Long>, JpaSpecificationExecutor<Commessa> {

}
