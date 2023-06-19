package it.prova.cogebe.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cogebe.model.Commessa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommessaDTO {

	private Long id;
	private String descrizione;
	private String codice;
	private LocalDate dataIn;
	private LocalDate dataOut;
	private Integer importo;

	private AziendaDTO azienda;
	private List<RisorsaDTO> risorse;

	public static CommessaDTO buildCommessaDTOFromModel(Commessa commessaModel) {
		if (commessaModel == null) {
			return null;
		}
		return CommessaDTO.builder().id(commessaModel.getId()).descrizione(commessaModel.getDescrizione())
				.codice(commessaModel.getCodice()).dataIn(commessaModel.getDataIn()).dataOut(commessaModel.getDataOut())
				.importo(commessaModel.getImporto()).build();
	}

	public static CommessaDTO buildCommessaDTOFromModelLazy(Commessa commessaModel) {
		if (commessaModel == null) {
			return null;
		}
		return CommessaDTO.builder().id(commessaModel.getId()).descrizione(commessaModel.getDescrizione())
				.codice(commessaModel.getCodice()).dataIn(commessaModel.getDataIn()).dataOut(commessaModel.getDataOut())
				.importo(commessaModel.getImporto()).build();
	}

	public static List<CommessaDTO> createCommessaDTOListFromModelList(List<Commessa> modelListInput) {
		return modelListInput.stream().map(CommessaDTO::buildCommessaDTOFromModelLazy).collect(Collectors.toList());
	}

	public static List<Commessa> createCommessaListFromDTOList(List<CommessaDTO> modelListInput, boolean eager) {
		return modelListInput.stream().map(CommessaDTO::buildCommessaModel).collect(Collectors.toList());
	}

	public Commessa buildCommessaModel() {
		if (azienda == null || azienda.getId() == null || risorse == null) {
			return null;
		}
		return Commessa.builder().id(this.id).descrizione(this.descrizione).codice(this.codice).dataIn(this.dataIn)
				.dataOut(this.dataOut).importo(this.importo)
				.risorse(RisorsaDTO.createRisorsaListFromDTOList(this.risorse))
				.azienda(this.azienda.buildAziendaModel()).build();
	}
}
