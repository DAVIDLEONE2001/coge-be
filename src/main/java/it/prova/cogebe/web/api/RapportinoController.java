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

import it.prova.cogebe.dto.RapportinoDTO;
import it.prova.cogebe.service.RapportinoService;

@CrossOrigin
@RestController
@RequestMapping("/api/rapportino")
public class RapportinoController {

	@Autowired
	private RapportinoService service;

	public List<RapportinoDTO> listAll() throws Exception {
		return service.listAll().stream().map(commessa -> RapportinoDTO.buildRapportinoDTOFromModel(commessa))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public RapportinoDTO caricaSingolo(@PathVariable(name = "id", required = true) Long id) throws Exception {
		return RapportinoDTO.buildRapportinoDTOFromModel(service.caricaSingoloElemento(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RapportinoDTO inserisciNuovoAtleta(@Valid @RequestBody RapportinoDTO input) throws Exception {

		return RapportinoDTO.buildRapportinoDTOFromModel(service.inserisciNuovo(input.buildRapportinoModel()));
	}

	@PutMapping("/{id}")
	public RapportinoDTO aggiornaAtleta(@Valid @RequestBody RapportinoDTO input) throws Exception {
		return RapportinoDTO.buildRapportinoDTOFromModel(service.aggiorna(input.buildRapportinoModel()));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void rimuovi(@PathVariable(name = "id", required = true) Long id) throws Exception {
		service.rimuovi(id);
	}
}
