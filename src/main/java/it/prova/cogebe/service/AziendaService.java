package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Azienda;

public interface AziendaService {

	public List<Azienda> listAll();

	public Azienda caricaSingolo(Long id);

	public Azienda aggiorna(Azienda aziendaInstance);

	public Azienda inserisciNuovo(Azienda aziendaInstance);

	public void rimuovi(Azienda aziendaInstance);

	public List<Azienda> cercaAziendaCostoDesc();

}
