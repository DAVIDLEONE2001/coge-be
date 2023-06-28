package it.prova.cogebe.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cogebe.model.Azienda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AziendaDTO {
	private Long id;

	@NotBlank(message = "{ragionesociale.notblank}")
	private String ragioneSociale;

	@NotBlank(message = "{partitaIva.notblank}")
	private String partitaIva;

	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;

	private List<CommessaDTO> commesse;

	public Azienda buildAziendaModel() {
		return Azienda.builder().id(this.id).ragioneSociale(this.ragioneSociale).partitaIva(this.partitaIva)
				.indirizzo(this.indirizzo).build();
	}

	public static AziendaDTO buildAziendaDTOFromModel(Azienda aziendaModel, boolean includesCommesse) {
		AziendaDTO result = AziendaDTO.builder().id(aziendaModel.getId())
				.ragioneSociale(aziendaModel.getRagioneSociale()).partitaIva(aziendaModel.getPartitaIva())
				.indirizzo(aziendaModel.getIndirizzo()).build();

		if (includesCommesse) {
			result.setCommesse(CommessaDTO.createCommessaDTOListFromModelSet(aziendaModel.getCommesse(), true, false));
		}
		return result;

	}

	public static List<AziendaDTO> createAziendaDTOListFromModelList(List<Azienda> modelListInput,
			boolean includesCommesse) {
		return modelListInput.stream().map(aziendaEntity -> {
			AziendaDTO result = AziendaDTO.buildAziendaDTOFromModel(aziendaEntity, includesCommesse);

			return result;
		}).collect(Collectors.toList());
	}

	public static AziendaDTO buildAziendaDTOFromModelLazy(Azienda aziendaModel) {

		AziendaDTO result = AziendaDTO.builder().id(aziendaModel.getId())
				.ragioneSociale(aziendaModel.getRagioneSociale()).partitaIva(aziendaModel.getPartitaIva())
				.indirizzo(aziendaModel.getIndirizzo()).build();
		return result;
	}

	public static AziendaDTO buildAziendaDTOFromModelSenzaCommesse(Azienda aziendaModel) {

		AziendaDTO result = AziendaDTO.builder().id(aziendaModel.getId())
				.ragioneSociale(aziendaModel.getRagioneSociale()).partitaIva(aziendaModel.getPartitaIva())
				.indirizzo(aziendaModel.getIndirizzo()).build();
		if (aziendaModel.getCommesse() != null) {
			result.setCommesse(CommessaDTO.createCommessaDTOListFromModelList(aziendaModel.getCommesse()));
		}
		return result;
	}

	public static Set<Azienda> createAziendaSetFromDTOList(List<AziendaDTO> modelListInput) {
		return modelListInput.stream().map(aziendaItem -> {
			return aziendaItem.buildAziendaModel();
		}).collect(Collectors.toSet());
	}

	public static List<Azienda> createAziendaListFromDTOList(List<AziendaDTO> modelListInput) {
		return modelListInput.stream().map(commessaDTOEntity -> {
			Azienda result = commessaDTOEntity.buildAziendaModel();
			return result;
		}).collect(Collectors.toList());
	}

	public static List<AziendaDTO> createAziendaListFromModelSet(Set<Azienda> modelSetInput, boolean includesCommesse) {
		return modelSetInput.stream().map(aziendaItem -> {
			return AziendaDTO.buildAziendaDTOFromModel(aziendaItem, includesCommesse);
		}).collect(Collectors.toList());
	}

}