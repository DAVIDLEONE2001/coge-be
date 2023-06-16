package it.prova.cogebe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.cogebe.model.Attachment;
import it.prova.cogebe.repository.AttachmentRepository;

@Service
@Transactional(readOnly = true)
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentRepository repository;
	
	@Override
	public List<Attachment> listAll(){
		return (List<Attachment>) repository.findAll();
	}

	@Override
	public Attachment caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Attachment aggiorna(Attachment attachmentInstance){
		return repository.save(attachmentInstance);
	}

	@Override
	@Transactional
	public Attachment inserisciNuovo(Attachment attachmentInstance) {
		return repository.save(attachmentInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idAttachment){
		repository.deleteById(idAttachment);
	}

}
