package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Commessa;

public interface CommessaService {

	public List<Commessa> listAll() throws Exception;

	public Commessa caricaSingoloElemento(Long id) throws Exception;

	public Commessa aggiorna(Commessa commessaInstance) throws Exception;

	public Commessa inserisciNuovo(Commessa commessaInstance) throws Exception;

	public void rimuovi(Long idAttachment) throws Exception;
}
