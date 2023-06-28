package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Rapportino;

public interface RapportinoService {

	public List<Rapportino> listAll();

	public List<Rapportino> listAllElementsEager();

	public Rapportino caricaSingoloElemento(Long id);

	public Rapportino caricaElementoEager(Long id);

	public Rapportino inserisciNuovo(Rapportino rapportinoInstance);

	public Rapportino aggiorna(Rapportino rapportinoInstance);

	public void rimuovi(Long idToDelete);

}
