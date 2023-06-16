package it.prova.cogebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.cogebe.model.Risorsa;
import it.prova.cogebe.repository.RisorsaRepository;

public class RisorsaServiceImpl implements RisorsaService {

	@Autowired
	private RisorsaRepository repository;
	
	@Override
	public List<Risorsa> listAll() throws Exception {
		return (List<Risorsa>) repository.findAll();
	}

	@Override
	public Risorsa caricaSingoloElemento(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception());
	}

	@Override
	public Risorsa aggiorna(Risorsa risorsaInstance) throws Exception {
		return repository.save(risorsaInstance);
	}

	@Override
	public Risorsa inserisciNuovo(Risorsa risorsaInstance) throws Exception {
		return repository.save(risorsaInstance);
	}

	@Override
	public void rimuovi(Long idRisorsa) throws Exception {
		repository.deleteById(idRisorsa);
	}

}
