package it.prova.cogebe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.cogebe.model.Risorsa;
import it.prova.cogebe.repository.AttachmentRepository;
import it.prova.cogebe.repository.CommessaRepository;
import it.prova.cogebe.repository.RisorsaRepository;

@Service
@Transactional(readOnly = true)
public class RisorsaServiceImpl implements RisorsaService {

	@Autowired
	private RisorsaRepository repository;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private CommessaRepository commessaRepository;

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

		Risorsa risorsaDirty = repository.findById(risorsaInstance.getId()).orElse(null);

		if (risorsaInstance.getCv() != null && risorsaInstance.getCv().getId() == null
				&& risorsaDirty.getCv() != null) {
			risorsaInstance.getCv().setId(risorsaDirty.getCv().getId());
			risorsaInstance.getCv().setRisorsa(risorsaInstance);
		} else if(risorsaInstance.getCv() != null) {
			risorsaInstance.getCv().setRisorsa(risorsaInstance);
			attachmentRepository.save(risorsaInstance.getCv());
		}

		if (risorsaInstance.getCommesse() != null) {
			if (!risorsaInstance.getCommesse().isEmpty()) {

				commessaRepository.findAllById(
						risorsaInstance.getCommesse().stream().map(i -> i.getId()).collect(Collectors.toList())).forEach(i->{
							i.getRisorse().add(risorsaInstance);
							commessaRepository.save(i);
						});
			}
		}
		
		return repository.save(risorsaInstance);
	}

	@Override
	@Transactional
	public Risorsa inserisciNuovo(Risorsa risorsaInstance) {
		if (risorsaInstance.getCv() != null && risorsaInstance.getCv().getId() == null) {
			risorsaInstance.getCv().setRisorsa(risorsaInstance);
			attachmentRepository.save(risorsaInstance.getCv());
		}
		return repository.save(risorsaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idRisorsa) {
		Risorsa risorsaInstance = repository.findById(idRisorsa).orElse(null);
		if (risorsaInstance.getCv() != null) {
			attachmentRepository.delete(risorsaInstance.getCv());
		}
		repository.deleteById(idRisorsa);
	}

}
