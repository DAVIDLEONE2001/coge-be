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
	public List<Azienda> listAll() throws Exception {
		return (List<Azienda>) repository.findAll();		
	}

	@Transactional
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
	
	@Transactional
	@Override
	public Azienda inserisciNuovo(Azienda aziendaInstance) throws Exception {
		return repository.save(aziendaInstance);
	}

	@Transactional
	@Override
	public void rimuovi(Long idAtleta) throws Exception {
		repository.deleteById(idAtleta);		
	}

}
