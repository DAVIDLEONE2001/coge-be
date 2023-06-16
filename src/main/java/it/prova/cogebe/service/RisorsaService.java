package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Risorsa;

public interface RisorsaService {

	public List<Risorsa> listAll() throws Exception;

	public Risorsa caricaSingoloElemento(Long id) throws Exception;

//	public Brano caricaSingoloElementoEagerGeneri(Long id) throws Exception;

	public Risorsa aggiorna(Risorsa risorsaInstance) throws Exception;

	public Risorsa inserisciNuovo(Risorsa risorsaInstance) throws Exception;

	public void rimuovi(Long idRisorsa) throws Exception;
	
}
