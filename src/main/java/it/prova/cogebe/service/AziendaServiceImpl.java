package it.prova.cogebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.cogebe.model.Azienda;
import it.prova.cogebe.repository.AziendaRepository;

@Service
@Transactional(readOnly = true)
public class AziendaServiceImpl implements AziendaService {

	@Autowired
	private AziendaRepository repository;

	@Override
	public List<Azienda> listAll() {
		return (List<Azienda>) repository.findAll();
	}

	@Override
	public Azienda caricaSingolo(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Azienda aggiorna(Azienda aziendaInstance) {

		return repository.save(aziendaInstance);
	}

	@Override
	@Transactional
	public Azienda inserisciNuovo(Azienda aziendaInstance) {
		return repository.save(aziendaInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Azienda aziendaInstance) {
		repository.delete(aziendaInstance);

	}

	@Override
	public List<Azienda> cercaAziendaCostoDesc() {
		return repository.findAziendeConCostoDesc();
	}

}