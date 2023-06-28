package it.prova.cogebe.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cogebe.model.Rapportino;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Json include, se un dato è nullo non cerrà messo nell'output
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RapportinoDTO {

	private Long id;

	@NotNull(message = "{numeroGiorni.notnull}")
	@Min(0)
	private Integer numeroGiorni;

	private CommessaDTO commessa;

	private RisorsaDTO risorsa;

	public Rapportino buildRapportinoModel() {
		Rapportino result = Rapportino.builder().id(this.id).numeroGiorni(this.numeroGiorni)
				.risorsa(this.risorsa.buildRisorsaModel()).build();
		return result;
	}

	public static RapportinoDTO buildRapportinoDTOFromModel(Rapportino rapportinoModel, boolean includeCommessa,
			boolean includeRisorsa) {
		RapportinoDTO result = RapportinoDTO.builder().id(rapportinoModel.getId())
				.numeroGiorni(rapportinoModel.getNumeroGiorni()).build();
		if (rapportinoModel.getCommessa() != null) {
			result.setCommessa(CommessaDTO.buildCommessaDTOFromModel(rapportinoModel.getCommessa(), false, false));
		}
		if (includeRisorsa) {
			result.setRisorsa(RisorsaDTO.buildRisorsaDTOFromModel(rapportinoModel.getRisorsa()));
		}
		return result;
	}

	public static RapportinoDTO buildRapportinoDTOFromModelLazy(Rapportino rapportinoModel) {
		if (rapportinoModel == null) {
			return null;
		}

		return RapportinoDTO.builder().id(rapportinoModel.getId()).numeroGiorni(rapportinoModel.getNumeroGiorni())
				.build();
	}

	public static List<RapportinoDTO> createRapportinoDTOListFromModelList(List<Rapportino> modelListInput,
			boolean includeCommessa, boolean includeRisorsa) {
		return modelListInput.stream().map(inputEntity -> {
			return RapportinoDTO.buildRapportinoDTOFromModel(inputEntity, includeCommessa, includeRisorsa);
		}).collect(Collectors.toList());
	}

	public static List<RapportinoDTO> createRapportinoDTOListFromModelSet(Set<Rapportino> modelListInput,
			boolean includeCommessa, boolean includeRisorsa) {
		return modelListInput.stream().map(rapportinoItem -> {
			return RapportinoDTO.buildRapportinoDTOFromModel(rapportinoItem, includeCommessa, includeRisorsa);
		}).collect(Collectors.toList());
	}

	public static Set<Rapportino> createRapportinoSetFromDTOList(List<RapportinoDTO> modelListInput) {
		return modelListInput.stream().map(rapportinoItem -> {
			return rapportinoItem.buildRapportinoModel();
		}).collect(Collectors.toSet());
	}

	public static List<Rapportino> createRapportinoListFromDTOList(List<RapportinoDTO> modelListInput, boolean eager) {
		return modelListInput.stream().map(RapportinoDTO::buildRapportinoModel).collect(Collectors.toList());
	}

}
