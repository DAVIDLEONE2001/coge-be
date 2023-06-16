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

import it.prova.cogebe.dto.RisorsaDTO;
import it.prova.cogebe.model.Risorsa;
import it.prova.cogebe.service.RisorsaService;

@RestController
@RequestMapping("/api/risorsa")
@CrossOrigin
public class RisorsaController {

	@Autowired
	private RisorsaService risorsaService;

	@GetMapping
	public List<RisorsaDTO> getAll() {
		// senza DTO qui hibernate dava il problema del N + 1 SELECT
		// (probabilmente dovuto alle librerie che serializzano in JSON)
		return RisorsaDTO.createRisorsaDTOListFromModelList(risorsaService.listAll());
	}

	@GetMapping("/{id}")
	public RisorsaDTO findById(@PathVariable(value = "id", required = true) long id) {
		Risorsa Risorsa = risorsaService.caricaSingoloElemento(id);

		if (Risorsa == null)
			throw new RuntimeException("Risorsa not found con id: " + id);

		return RisorsaDTO.buildRisorsaDTOFromModel(Risorsa);
	}

//
//	// gli errori di validazione vengono mostrati con 400 Bad Request ma
//	// elencandoli grazie al ControllerAdvice
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RisorsaDTO createNew(@Valid @RequestBody RisorsaDTO RisorsaInput) {
//		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
//		// non sta bene
		if (RisorsaInput.getId() != null)
			throw new RuntimeException("Non è ammesso fornire un id per la creazione");

		;
		Risorsa RisorsaAggiornato = risorsaService.inserisciNuovo(RisorsaInput.buildRisorsaModel());
		return RisorsaDTO.buildRisorsaDTOFromModel(risorsaService.caricaSingoloElemento(RisorsaAggiornato.getId()));
	}

	@PutMapping("/{id}")
	public RisorsaDTO update(@Valid @RequestBody RisorsaDTO RisorsaInput, @PathVariable(required = true) Long id) {
		Risorsa Risorsa = risorsaService.caricaSingoloElemento(id);

		if (Risorsa == null)
			throw new RuntimeException("Risorsa not found con id: " + id);

		RisorsaInput.setId(id);
		Risorsa RisorsaAggiornato = risorsaService.aggiorna(RisorsaInput.buildRisorsaModel());
		return RisorsaDTO.buildRisorsaDTOFromModel(RisorsaAggiornato);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		risorsaService.rimuovi(id);
	}
	
}
