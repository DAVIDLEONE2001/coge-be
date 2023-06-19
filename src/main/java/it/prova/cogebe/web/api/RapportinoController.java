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
import it.prova.cogebe.model.Rapportino;
import it.prova.cogebe.service.RapportinoService;

@CrossOrigin
@RestController
@RequestMapping("/api/rapportino")
public class RapportinoController {

	private final RapportinoService rapportinoService;

	@Autowired
	public RapportinoController(RapportinoService rapportinoService) {
		this.rapportinoService = rapportinoService;
	}

	@GetMapping
	public List<RapportinoDTO> listAll() throws Exception {
		List<Rapportino> rapportini = rapportinoService.listAll();
		return rapportini.stream().map(RapportinoDTO::buildRapportinoDTOFromModel).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public RapportinoDTO getRapportino(@PathVariable("id") Long id) throws Exception {
		Rapportino rapportino = rapportinoService.caricaSingoloElemento(id);
		return RapportinoDTO.buildRapportinoDTOFromModel(rapportino);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RapportinoDTO createRapportino(@Valid @RequestBody RapportinoDTO rapportinoDTO) throws Exception {
		Rapportino rapportino = rapportinoDTO.buildRapportinoModel();
		rapportino = rapportinoService.inserisciNuovo(rapportino);
		return RapportinoDTO.buildRapportinoDTOFromModel(rapportino);
	}

	@PutMapping("/{id}")
	public RapportinoDTO updateRapportino(@PathVariable("id") Long id, @Valid @RequestBody RapportinoDTO rapportinoDTO)
			throws Exception {
		Rapportino rapportino = rapportinoDTO.buildRapportinoModel();
		rapportino.setId(id);
		rapportino = rapportinoService.aggiorna(rapportino);
		return RapportinoDTO.buildRapportinoDTOFromModel(rapportino);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRapportino(@PathVariable("id") Long id) throws Exception {
		rapportinoService.rimuovi(id);
	}
}
