package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.dto.ICommessaMargineDTO;
import it.prova.cogebe.model.Commessa;

public interface CommessaService {

	public List<Commessa> listAll();

	public Commessa caricaSingoloElemento(Long id);

	public Commessa inserisciNuovo(Commessa commessaInstance);

	public Commessa aggiorna(Commessa commessaInstance);

	public Commessa caricaSingoloEager(Long id);

	public void rimuovi(Long idToDelete);

	public List<ICommessaMargineDTO> commesseChiuseConMargineDecrescente();

}
