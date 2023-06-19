package it.prova.cogebe.service;

import java.util.List;

import it.prova.cogebe.model.Rapportino;

public interface RapportinoService {

	public List<Rapportino> listAll();

	public Rapportino caricaSingoloElemento(Long id);

	public Rapportino aggiorna(Rapportino rapportinoInstance);

	public Rapportino inserisciNuovo(Rapportino rapportinoInstance);

	public void rimuovi(Long idRapportino) throws Exception;
<<<<<<< Updated upstream

	public List<Rapportino> listByCommessaId(Long commessaId);

=======
>>>>>>> Stashed changes
}
