package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Risorsa;

public interface RisorsaService {

	public List<Risorsa> listAll();

	public Risorsa caricaSingoloElemento(Long id);

//	public Brano caricaSingoloElementoEagerGeneri(Long id) throws Exception;

	public Risorsa aggiorna(Risorsa risorsaInstance);

	public Risorsa inserisciNuovo(Risorsa risorsaInstance);

	public void rimuovi(Long idRisorsa);
	
}
