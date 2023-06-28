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

import it.prova.cogebe.dto.RapportinoDTO;
import it.prova.cogebe.dto.RapportinoPerInsertDTO;
import it.prova.cogebe.model.Commessa;
import it.prova.cogebe.model.Rapportino;
import it.prova.cogebe.model.Risorsa;
import it.prova.cogebe.service.CommessaService;
import it.prova.cogebe.service.RapportinoService;
import it.prova.cogebe.service.RisorsaService;

@RestController
@RequestMapping("api/rapportino")
@CrossOrigin
public class RapportinoController {

	@Autowired
	private RapportinoService rapportinoService;

	@Autowired
	private RisorsaService risorsaService;

	@Autowired
	private CommessaService commessaService;

	@GetMapping
	public List<RapportinoDTO> visualizzaRapportini() {
		return RapportinoDTO.createRapportinoDTOListFromModelList(rapportinoService.listAllElementsEager(), false,
				true);

	}

	@GetMapping("/{id}")
	public RapportinoDTO visualizza(@PathVariable(required = true) Long id) {
		return RapportinoDTO.buildRapportinoDTOFromModel(rapportinoService.caricaElementoEager(id), true, true);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RapportinoDTO createNew(@Valid @RequestBody RapportinoPerInsertDTO rapportinoInput) {
		if (rapportinoInput.getId() != null) {
			throw new RuntimeException();
		}
		Risorsa risorsaCaricata = risorsaService.caricaSingoloElemento(rapportinoInput.getRisorsa());
		Commessa commessaCaricata = commessaService.caricaSingoloElemento(rapportinoInput.getCommessa());
		Rapportino rapportino = new Rapportino();
		rapportino.setNumeroGiorni(rapportinoInput.getNumeroGiorni());
		rapportino.setRisorsa(risorsaCaricata);
		rapportino.setCommessa(commessaCaricata);
		Rapportino rapportinoInserito = rapportinoService.inserisciNuovo(rapportino);
//			rapportinoInserito.setRisorsa(risorsaCaricata);

		return RapportinoDTO.buildRapportinoDTOFromModel(rapportinoInserito, false, false);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RapportinoDTO update(@PathVariable Long id, @Valid @RequestBody RapportinoPerInsertDTO rapportinoInput) {
		Rapportino rapportinoEsistente = rapportinoService.caricaSingoloElemento(id);
		if (rapportinoEsistente == null) {
			throw new RuntimeException("Rapportino non trovato con id: " + id);
		}
		Risorsa risorsaCaricata = risorsaService.caricaSingoloElemento(rapportinoInput.getRisorsa());
		Commessa commessaCaricata = commessaService.caricaSingoloElemento(rapportinoInput.getCommessa());
		rapportinoEsistente.setNumeroGiorni(rapportinoInput.getNumeroGiorni());
		rapportinoEsistente.setRisorsa(risorsaCaricata);
		rapportinoEsistente.setCommessa(commessaCaricata);
		Rapportino rapportinoAggiornato = rapportinoService.aggiorna(rapportinoEsistente);

		return RapportinoDTO.buildRapportinoDTOFromModel(rapportinoAggiornato, false, false);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable(required = true) Long id) {
		rapportinoService.rimuovi(id);
	}
}
