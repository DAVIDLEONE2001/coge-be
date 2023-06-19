package it.prova.cogebe.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.cogebe.model.Commessa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
		return CommessaDTO.builder().id(commessaModel.getId()).descrizione(commessaModel.getDescrizione())
				.codice(commessaModel.getCodice()).dataIn(commessaModel.getDataIn()).dataOut(commessaModel.getDataOut())
				.importo(commessaModel.getImporto())
				.risorse(RisorsaDTO.createRisorsaDTOListFromModelList(commessaModel.getRisorse()))
				.azienda(AziendaDTO.buildAziendaDTOFromModel(commessaModel.getAzienda())).build();
	}

	public static List<CommessaDTO> createCommessaDTOListFromModelList(List<Commessa> modelListInput) {
		return modelListInput.stream().map(CommessaDTO::buildCommessaDTOFromModel).collect(Collectors.toList());
	}

	public static List<Commessa> createCommessaListFromDTOList(List<CommessaDTO> modelListInput) {
		return modelListInput.stream().map(CommessaDTO::buildCommessaModel).collect(Collectors.toList());
	}

	public Commessa buildCommessaModel() {
		return Commessa.builder().id(this.id).descrizione(this.descrizione).codice(this.codice).dataIn(this.dataIn)
				.dataOut(this.dataOut).importo(this.importo)
				.risorse(RisorsaDTO.createRisorsaListFromDTOList(this.risorse)).build();
	}

}
