package it.prova.cogebe.web.api;

import java.util.List;
import java.util.stream.Collectors;

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
import it.prova.cogebe.service.AziendaService;


@CrossOrigin
@RestController
@RequestMapping("/api/azienda")
public class AziendaController {

	@Autowired
	private AziendaService service;

	
	@GetMapping
	public List<AziendaDTO> listAll() throws Exception {
		return service.listAll().stream().map(atleta -> AziendaDTO.buildAziendaDTOFromModel(atleta))
				.collect(Collectors.toList());
	}
	

	@GetMapping("/{id}")
	public AziendaDTO caricaSingolo(@PathVariable(name = "id", required = true) Long id) throws Exception {
		return AziendaDTO.buildAziendaDTOFromModel(service.caricaSingoloElemento(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AziendaDTO inserisciNuovoAtleta(@Valid @RequestBody AziendaDTO input) throws Exception {

		return AziendaDTO.buildAziendaDTOFromModel(service.inserisciNuovo(input.buildAziendaModel()));
	}
	
	@PutMapping("/{id}")
	public AziendaDTO aggiornaAtleta(@Valid @RequestBody AziendaDTO input) throws Exception {
		return AziendaDTO.buildAziendaDTOFromModel(service.aggiorna(input.buildAziendaModel()));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void rimuovi(@PathVariable(name = "id", required = true) Long id) throws Exception {
		service.rimuovi(id);
	}
	
}
