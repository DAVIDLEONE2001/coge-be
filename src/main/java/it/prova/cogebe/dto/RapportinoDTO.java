package it.prova.cogebe.dto;

import java.util.List;
import java.util.stream.Collectors;

import it.prova.cogebe.model.Rapportino;
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

	private RisorsaDTO risorsa;
	private CommessaDTO commessa;

	public static RapportinoDTO buildRapportinoDTOFromModel(Rapportino rapportinoModel) {
		if (rapportinoModel == null) {
			return null;
		}

		return RapportinoDTO.builder().id(rapportinoModel.getId()).numeroGiorni(rapportinoModel.getNumeroGiorni())
				.build();
	}
	
	public static RapportinoDTO buildRapportinoDTOFromModelLazy(Rapportino rapportinoModel) {
		if (rapportinoModel == null) {
			return null;
		}

		return RapportinoDTO.builder().id(rapportinoModel.getId()).numeroGiorni(rapportinoModel.getNumeroGiorni())
				.build();
	}

	public static List<RapportinoDTO> createRapportinoDTOListFromModelList(List<Rapportino> modelListInput) {
		return modelListInput.stream().map(RapportinoDTO::buildRapportinoDTOFromModel).collect(Collectors.toList());
	}

	public static List<Rapportino> createRapportinoListFromDTOList(List<RapportinoDTO> modelListInput) {
		return modelListInput.stream().map(RapportinoDTO::buildRapportinoModel).collect(Collectors.toList());
	}

	public Rapportino buildRapportinoModel() {
		return Rapportino.builder().id(this.id).numeroGiorni(this.numeroGiorni)
				.risorsa(this.risorsa != null ? this.risorsa.buildRisorsaModel() : null)
				.commessa(this.commessa != null ? this.commessa.buildCommessaModel() : null).build();
	}
}
