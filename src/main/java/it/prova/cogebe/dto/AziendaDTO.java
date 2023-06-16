package it.prova.cogebe.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.cogebe.model.Azienda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AziendaDTO {

	private Long id;

	@NotBlank(message = "{ragioneSociale.notblank}")
	private String ragioneSociale;

	@NotBlank(message = "{partitaIva.notblank}")
	private String partitaIva;

	@NotBlank(message = "{partitaIva.notblank}")
	private String indirizzo;

	List<CommessaDTO> commesse;

	public static AziendaDTO buildAziendaDTOFromModel(Azienda aziendaModel) {

		AziendaDTO result = AziendaDTO.builder().id(aziendaModel.getId())
				.ragioneSociale(aziendaModel.getRagioneSociale()).partitaIva(aziendaModel.getPartitaIva())
				.indirizzo(aziendaModel.getIndirizzo())
				.commesse(CommessaDTO.createCommessaDTOListFromModelList(aziendaModel.getCommesse())).build();

		return result;
	}

	public static List<AziendaDTO> createAziendaDTOListFromModelList(List<Azienda> modelListInput) {
		return modelListInput.stream().map(aziendaEntity -> {
			AziendaDTO result = AziendaDTO.buildAziendaDTOFromModel(aziendaEntity);
			return result;
		}).collect(Collectors.toList());
	}

	public Azienda buildAziendaModel() {
		Azienda result = Azienda.builder().id(this.id).ragioneSociale(this.ragioneSociale).partitaIva(this.partitaIva)
				.indirizzo(this.indirizzo).build();

		return result;

	}

	public static List<Azienda> createAziendaListFromDTOList(List<AziendaDTO> modelListInput) {
		return modelListInput.stream().map(commessaDTOEntity -> {
			Azienda result = commessaDTOEntity.buildAziendaModel();
			return result;
		}).collect(Collectors.toList());
	}

}