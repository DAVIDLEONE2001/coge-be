package it.prova.cogebe.service;

import java.util.List;


import it.prova.cogebe.model.Azienda;
import it.prova.cogebe.repository.AziendaRepository;

public class AziendaServiceImpl implements AziendaService {
	
	private AziendaRepository repository;

	@Override
	public List<Azienda> listAll() throws Exception {
		return (List<Azienda>) repository.findAll();		
	}

	@Override
	public Azienda caricaSingoloElemento(Long id) throws Exception {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Azienda aggiorna(Azienda aziendaInstance) throws Exception {
		if (caricaSingoloElemento(aziendaInstance.getId()) != null) {
			return repository.save(aziendaInstance);
		}
		return null;
		
	}

	@Override
	public Azienda inserisciNuovo(Azienda aziendaInstance) throws Exception {
		return repository.save(aziendaInstance);
	}

	@Override
	public void rimuovi(Long idAtleta) throws Exception {
		repository.deleteById(idAtleta);		
	}

}
