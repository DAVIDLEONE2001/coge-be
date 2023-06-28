package it.prova.cogebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.cogebe.model.Rapportino;
import it.prova.cogebe.repository.RapportinoRepository;

@Service
@Transactional(readOnly = true)
public class RapportinoServiceImpl implements RapportinoService {
	@Autowired
	private RapportinoRepository repository;

	@Override
	public List<Rapportino> listAll() {
		return (List<Rapportino>) repository.findAll();
	}

	@Override
	public Rapportino caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Rapportino inserisciNuovo(Rapportino rapportinoInstance) {
		return repository.save(rapportinoInstance);
	}

	@Override
	@Transactional
	public Rapportino aggiorna(Rapportino rapportinoInstance) {
		return repository.save(rapportinoInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToDelete) {
		repository.deleteById(idToDelete);

	}

	@Override
	public List<Rapportino> listAllElementsEager() {
		return repository.getAllEagerWithRisorsa();
	}

	@Override
	public Rapportino caricaElementoEager(Long id) {
		return repository.getSingleEager(id);
	}

}