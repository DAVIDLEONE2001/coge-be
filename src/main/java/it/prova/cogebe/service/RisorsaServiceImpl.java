package it.prova.cogebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.cogebe.model.Risorsa;
import it.prova.cogebe.repository.RisorsaRepository;

@Service
@Transactional(readOnly = true)
public class RisorsaServiceImpl implements RisorsaService {

	@Autowired
	private RisorsaRepository repository;

	@Override
	public List<Risorsa> listAll() {
		return (List<Risorsa>) repository.findAll();
	}

	@Override
	public Risorsa caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Risorsa aggiorna(Risorsa risorsaInstance) {
		return repository.save(risorsaInstance);
	}

	@Override
	@Transactional
	public Risorsa inserisciNuovo(Risorsa risorsaInstance) {
		return repository.save(risorsaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idRisorsa) {
		repository.deleteById(idRisorsa);
	}

}
