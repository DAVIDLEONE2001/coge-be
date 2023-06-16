package it.prova.cogebe.dto;

import java.util.List;
import java.util.stream.Collectors;

import it.prova.cogebe.model.Commessa;
import it.prova.cogebe.model.Rapportino;
import it.prova.cogebe.model.Risorsa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RapportinoDTO {

	private Long id;
	private Integer numeroGiorni;

	private Risorsa risorsa;
	private Commessa commessa;

	public static RapportinoDTO buildRapportinoDTOFromModel(Rapportino rapportinoModel) {
		return RapportinoDTO.builder().id(rapportinoModel.getId()).numeroGiorni(rapportinoModel.getNumeroGiorni())
				.risorsa(rapportinoModel.getRisorsa()).commessa(rapportinoModel.getCommessa()).build();
	}

	public static List<RapportinoDTO> createRapportinoDTOListFromModelList(List<Rapportino> modelListInput) {
		return modelListInput.stream().map(RapportinoDTO::buildRapportinoDTOFromModel).collect(Collectors.toList());
	}
	
	public static List<Rapportino> createRapportinoListFromDTOList(List<RapportinoDTO> modelListInput) {
	    return modelListInput.stream()
	            .map(rapportinoDTO -> rapportinoDTO.buildRapportinoModel())
	            .collect(Collectors.toList());
	}

	public Rapportino buildRapportinoModel() {
		return Rapportino.builder().id(this.id).numeroGiorni(this.numeroGiorni).risorsa(this.risorsa)
				.commessa(this.commessa).build();
	}
}
