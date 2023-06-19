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

import it.prova.cogebe.dto.CommessaDTO;
import it.prova.cogebe.service.CommessaService;

@CrossOrigin
@RestController
@RequestMapping("/api/commessa")
public class CommessaController {

	@Autowired
	private CommessaService service;

	@GetMapping
	public List<CommessaDTO> listAll() throws Exception {
		return service.listAll().stream().map(commessa -> CommessaDTO.buildCommessaDTOFromModel(commessa))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public CommessaDTO caricaSingolo(@PathVariable(name = "id", required = true) Long id) throws Exception {
		return CommessaDTO.buildCommessaDTOFromModel(service.caricaSingoloElemento(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommessaDTO inserisciNuovaCommessa(@Valid @RequestBody CommessaDTO input) throws Exception {

		return CommessaDTO.buildCommessaDTOFromModel(service.inserisciNuovo(input.buildCommessaModel()));
	}

	@PutMapping("/{id}")
	public CommessaDTO aggiornaCommessa(@Valid @RequestBody CommessaDTO input) throws Exception {
		return CommessaDTO.buildCommessaDTOFromModel(service.aggiorna(input.buildCommessaModel()));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void rimuovi(@PathVariable(name = "id", required = true) Long id) throws Exception {
		service.rimuovi(id);
	}

}
