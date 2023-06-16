package it.prova.cogebe.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.cogebe.dto.AttachmentDTO;
import it.prova.cogebe.model.Attachment;
import it.prova.cogebe.service.AttachmentService;

@RestController
@RequestMapping("/api/attachment")
@CrossOrigin
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;

	@GetMapping
	public List<AttachmentDTO> getAll() {
		// senza DTO qui hibernate dava il problema del N + 1 SELECT
		// (probabilmente dovuto alle librerie che serializzano in JSON)
		return AttachmentDTO.createAttachmentDTOListFromModelList(attachmentService.listAll());
	}

	@GetMapping("/{id}")
	public AttachmentDTO findById(@PathVariable(value = "id", required = true) long id) {
		Attachment Attachment = attachmentService.caricaSingoloElemento(id);

		if (Attachment == null)
			throw new RuntimeException("Attachment not found con id: " + id);

		return AttachmentDTO.buildAttachmentDTOFromModel(Attachment);
	}

//
//	// gli errori di validazione vengono mostrati con 400 Bad Request ma
//	// elencandoli grazie al ControllerAdvice
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AttachmentDTO createNew(@Valid @RequestBody AttachmentDTO AttachmentInput) {
//		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
//		// non sta bene
		if (AttachmentInput.getId() != null)
			throw new RuntimeException("Non è ammesso fornire un id per la creazione");

		;
		Attachment AttachmentAggiornato = attachmentService.inserisciNuovo(AttachmentInput.buildAttachmentModel());
		return AttachmentDTO.buildAttachmentDTOFromModel(attachmentService.caricaSingoloElemento(AttachmentAggiornato.getId()));
	}

	@PutMapping("/{id}")
	public AttachmentDTO update(@Valid @RequestBody AttachmentDTO AttachmentInput, @PathVariable(required = true) Long id) {
		Attachment Attachment = attachmentService.caricaSingoloElemento(id);

		if (Attachment == null)
			throw new RuntimeException("Attachment not found con id: " + id);

		AttachmentInput.setId(id);
		Attachment AttachmentAggiornato = attachmentService.aggiorna(AttachmentInput.buildAttachmentModel());
		return AttachmentDTO.buildAttachmentDTOFromModel(AttachmentAggiornato);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		attachmentService.rimuovi(id);
	}
	
}
