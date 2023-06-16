package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Azienda;

public interface AziendaService {

	public List<Azienda> listAll() throws Exception;

	public Azienda caricaSingoloElemento(Long id) throws Exception;

	public Azienda aggiorna(Azienda aziendaInstance) throws Exception;

	public Azienda inserisciNuovo(Azienda aziendaInstance) throws Exception;

	public void rimuovi(Long idAtleta) throws Exception;
	
}
