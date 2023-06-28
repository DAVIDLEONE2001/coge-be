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

import it.prova.cogebe.dto.AziendaDTO;
import it.prova.cogebe.model.Azienda;
import it.prova.cogebe.service.AziendaService;

@RestController
@RequestMapping("api/azienda")
@CrossOrigin
public class AziendaController {

	@Autowired
	private AziendaService aziendaService;

	@GetMapping
	public List<AziendaDTO> getAll() {
		return AziendaDTO.createAziendaDTOListFromModelList(aziendaService.listAll(), false);
	}

	@GetMapping("/{id}")
	public AziendaDTO caricaSingolo(@PathVariable(required = true) Long id) {
		return AziendaDTO.buildAziendaDTOFromModel(aziendaService.caricaSingolo(id), false);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AziendaDTO inserisciNuovo(@Valid @RequestBody AziendaDTO aziendaInstance) {
		return AziendaDTO.buildAziendaDTOFromModel(aziendaService.inserisciNuovo(aziendaInstance.buildAziendaModel()),
				false);

	}

	@PutMapping("/{id}")
	public AziendaDTO update(@Valid @RequestBody AziendaDTO aziendaInstance, @PathVariable(required = true) Long id) {
		aziendaInstance.setId(id);

		return AziendaDTO.buildAziendaDTOFromModel(aziendaService.aggiorna(aziendaInstance.buildAziendaModel()), false);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		Azienda aziendaDaEliminare = aziendaService.caricaSingolo(id);
		aziendaService.rimuovi(aziendaDaEliminare);
		AziendaDTO.buildAziendaDTOFromModel(aziendaDaEliminare, false);
	}

	@GetMapping(value = "/aziendacostodesc")
	public List<AziendaDTO> aziendaConImportoDesc() {
		List<Azienda> listaAziendeInOrdine = aziendaService.cercaAziendaCostoDesc();

		return AziendaDTO.createAziendaDTOListFromModelList(listaAziendeInOrdine, false);

	}

}