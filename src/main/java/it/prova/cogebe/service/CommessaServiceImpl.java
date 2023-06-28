package it.prova.cogebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.cogebe.dto.ICommessaMargineDTO;
import it.prova.cogebe.model.Commessa;
import it.prova.cogebe.repository.AziendaRepository;
import it.prova.cogebe.repository.CommessaRepository;

@Service
@Transactional(readOnly = true)
public class CommessaServiceImpl implements CommessaService {

	@Autowired
	private CommessaRepository repository;

	@Autowired
	private AziendaRepository aziendaRepository;

	@Override
	public List<Commessa> listAll() {

		return (List<Commessa>) repository.findAll();
	}

	@Override
	public Commessa caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Commessa inserisciNuovo(Commessa commessaInstance) {

		return repository.save(commessaInstance);
	}

	@Override
	@Transactional
	public Commessa aggiorna(Commessa commessaInstance) {
		return repository.save(commessaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToDelete) {
		repository.deleteById(idToDelete);

	}

	@Override
	public List<ICommessaMargineDTO> commesseChiuseConMargineDecrescente() {
		return repository.commesseChiuseConMargineDecrescente();
	}

	@Override
	public Commessa caricaSingoloEager(Long id) {
		return repository.getSingleEager(id);
	}

}