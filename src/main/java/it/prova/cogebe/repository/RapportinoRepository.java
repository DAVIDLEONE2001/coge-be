package it.prova.cogebe.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import it.prova.cogebe.model.Rapportino;

public interface RapportinoRepository extends CrudRepository<Rapportino, Long>, JpaSpecificationExecutor<Rapportino> {

}
