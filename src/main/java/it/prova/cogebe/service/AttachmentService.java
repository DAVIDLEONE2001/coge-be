package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Attachment;

public interface AttachmentService {

	public List<Attachment> listAll() throws Exception;

	public Attachment caricaSingoloElemento(Long id) throws Exception;

//	public Brano caricaSingoloElementoEagerGeneri(Long id) throws Exception;

	public Attachment aggiorna(Attachment attachmentInstance) throws Exception;

	public Attachment inserisciNuovo(Attachment attachmentInstance) throws Exception;

	public void rimuovi(Long idAttachment) throws Exception;
	
}
