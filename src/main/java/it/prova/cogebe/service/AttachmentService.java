package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Attachment;

public interface AttachmentService {

	public List<Attachment> listAll();

	public Attachment caricaSingoloElemento(Long id);

//	public Brano caricaSingoloElementoEagerGeneri(Long id) throws Exception;

	public Attachment aggiorna(Attachment attachmentInstance);

	public Attachment inserisciNuovo(Attachment attachmentInstance);

	public void rimuovi(Long idAttachment);
	
}
