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

import it.prova.cogebe.dto.CommessaDTO;
import it.prova.cogebe.dto.CommessaPerInsertDTO;
import it.prova.cogebe.dto.ICommessaMargineDTO;
import it.prova.cogebe.model.Azienda;
import it.prova.cogebe.model.Commessa;
import it.prova.cogebe.service.AziendaService;
import it.prova.cogebe.service.CommessaService;

@RestController
@RequestMapping("api/commessa")
@CrossOrigin
public class CommessaController {

	@Autowired
	private CommessaService commessaService;

	@Autowired
	private AziendaService aziendaService;

	@GetMapping
	public List<CommessaDTO> visualizzaCommesse() {
		return CommessaDTO.createCommessaDTOListFromModelList(commessaService.listAll(), true, false);

	}

	@GetMapping("/{id}")
	public CommessaDTO visualizza(@PathVariable(required = true) Long id) {
		return CommessaDTO.buildCommessaDTOFromModel(commessaService.caricaSingoloEager(id), true, true);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommessaDTO createNew(@Valid @RequestBody CommessaPerInsertDTO commessaInput) {
		if (commessaInput.getId() != null) {
			throw new RuntimeException();
		}
		Azienda aziendaCaricata = aziendaService.caricaSingolo(commessaInput.getAzienda());
		Commessa commessa = new Commessa();
		commessa.setDescrizione(commessaInput.getDescrizione());
		commessa.setCodice(commessaInput.getCodice());
		commessa.setDataIn(commessaInput.getDataIn());
		commessa.setDataOut(commessaInput.getDataOut());
		commessa.setImporto(commessaInput.getImporto());
		commessa.setAzienda(aziendaCaricata);

		Commessa commessaInserita = commessaService.inserisciNuovo(commessa);
		return CommessaDTO.buildCommessaDTOFromModel(commessaInserita, false, false);

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CommessaDTO update(@PathVariable Long id, @Valid @RequestBody CommessaPerInsertDTO commessaInput) {

		Commessa commessaEsistente = commessaService.caricaSingoloElemento(id);
		if (commessaEsistente == null) {
			throw new RuntimeException("Commessa non trovata con id: " + id);
		}

		Azienda aziendaCaricata = aziendaService.caricaSingolo(commessaInput.getAzienda());
		commessaEsistente.setDescrizione(commessaInput.getDescrizione());
		commessaEsistente.setCodice(commessaInput.getCodice());
		commessaEsistente.setDataIn(commessaInput.getDataIn());
		commessaEsistente.setDataOut(commessaInput.getDataOut());
		commessaEsistente.setImporto(commessaInput.getImporto());
		commessaEsistente.setAzienda(aziendaCaricata);

		Commessa commessaAggiornata = commessaService.aggiorna(commessaEsistente);

		return CommessaDTO.buildCommessaDTOFromModel(commessaAggiornata, false, false);

	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable(required = true) Long id) {
		commessaService.rimuovi(id);
	}

	@GetMapping("/commessechiusemarginedecrescente")
	public List<ICommessaMargineDTO> commesseChiuseConMargineDecrescente() {
		List<ICommessaMargineDTO> listaCommesseChiuseConMargineDecr = commessaService
				.commesseChiuseConMargineDecrescente();

		return listaCommesseChiuseConMargineDecr;

	}

}