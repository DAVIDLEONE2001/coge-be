package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Commessa;

public interface CommessaService {

	List<Commessa> listAll();

	Commessa caricaSingoloElemento(Long id);

	Commessa aggiorna(Commessa commessaInstance);

	Commessa inserisciNuovo(Commessa commessaInstance);

	void rimuovi(Long idCommessa);

	// Aggiungi i seguenti metodi

	List<Commessa> getCommesseChiuseOrderByMargineDecrescente();

	List<Commessa> getCommesseByAzienda(Long idAzienda);

	double getMargineCommessa(Long idCommessa);

}
