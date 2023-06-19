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
	public List<Commessa> listAll() {
		return (List<Commessa>) repository.findAll();
	}

	@Override
	public Commessa caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Commessa aggiorna(Commessa commessaInstance) {
		return repository.save(commessaInstance);
	}

	@Transactional
	@Override
	public Commessa inserisciNuovo(Commessa commessaInstance) {
		return repository.save(commessaInstance);
	}

	@Transactional
	@Override
	public void rimuovi(Long idRapportino) {
		repository.deleteById(idRapportino);
<<<<<<< Updated upstream
	}

	@Override
	public List<Commessa> getCommesseChiuseOrderByMargineDecrescente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commessa> getCommesseByAzienda(Long idAzienda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMargineCommessa(Long idCommessa) {
		// TODO Auto-generated method stub
		return 0;
=======
>>>>>>> Stashed changes
	}
}