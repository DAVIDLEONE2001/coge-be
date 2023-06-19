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

	@Transactional
	@Override
	public Rapportino aggiorna(Rapportino rapportinoInstance) {
		return repository.save(rapportinoInstance);
	}

	@Transactional
	@Override
	public Rapportino inserisciNuovo(Rapportino rapportinoInstance) {
		return repository.save(rapportinoInstance);
	}

	@Transactional
	@Override
	public void rimuovi(Long idRapportino) throws Exception {
		repository.deleteById(idRapportino);

	}

	@Override
	public List<Rapportino> listByCommessaId(Long commessaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
