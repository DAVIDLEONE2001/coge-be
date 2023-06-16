package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Commessa;

public interface CommessaService {

	public List<Commessa> listAll();

	public Commessa caricaSingoloElemento(Long id);

	public Commessa aggiorna(Commessa commessaInstance);

	public Commessa inserisciNuovo(Commessa commessaInstance);

	public void rimuovi(Long idCommessa);
}
