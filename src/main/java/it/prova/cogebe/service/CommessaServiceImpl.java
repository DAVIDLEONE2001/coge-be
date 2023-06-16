package it.prova.cogebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.cogebe.model.Commessa;
import it.prova.cogebe.repository.CommessaRepository;

@Service
@Transactional(readOnly = true)
public class CommessaServiceImpl implements CommessaService {

	@Autowired
	private CommessaRepository repository;

	@Override
	public List<Commessa> listAll() throws Exception {
		return (List<Commessa>) repository.findAll();
	}

	@Override
	public Commessa caricaSingoloElemento(Long id) throws Exception {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Commessa aggiorna(Commessa commessaInstance) throws Exception {
		if (commessaInstance == null || commessaInstance.getId() == null
				|| caricaSingoloElemento(commessaInstance.getId()) == null) {
			throw new Exception("Impossibile aggiornare la commessa.");
		}
		return repository.save(commessaInstance);
	}

	@Transactional
	@Override
	public Commessa inserisciNuovo(Commessa commessaInstance) throws Exception {
		if (commessaInstance == null) {
			throw new Exception("Impossibile inserire una commessa nulla.");
		}
		return repository.save(commessaInstance);
	}

	@Transactional
	@Override
	public void rimuovi(Long id) throws Exception {
		if (id == null || caricaSingoloElemento(id) == null) {
			throw new Exception("Impossibile rimuovere la commessa.");
		}
		repository.deleteById(id);
	}
}