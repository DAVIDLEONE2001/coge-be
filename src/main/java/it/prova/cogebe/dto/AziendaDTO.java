package it.prova.cogebe.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.cogebe.model.Azienda;
import it.prova.cogebe.model.Commessa;
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

	List<Commessa> commesse;

	public static AziendaDTO buildAziendaDTOFromModel(Azienda aziendaModel) {

		AziendaDTO result = AziendaDTO.builder().id(aziendaModel.getId())
				.ragioneSociale(aziendaModel.getRagioneSociale()).partitaIva(aziendaModel.getPartitaIva())
				.indirizzo(aziendaModel.getIndirizzo()).build();

//	.commesse(atletaModel.getNumeroMedaglieVinte()).build();
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

}