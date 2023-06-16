package it.prova.cogebe.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
public class CommessaDTO {

	private Long id;
	private String descrizione;
	private String codice;
	private LocalDate dataIn;
	private LocalDate dataOut;
	private Integer importo;

	private Azienda azienda;
	private List<RisorsaDTO> risorse;

	public static CommessaDTO buildCommessaDTOFromModel(Commessa commessaModel) {

		CommessaDTO result = CommessaDTO.builder().id(commessaModel.getId()).descrizione(commessaModel.getDescrizione())
				.codice(commessaModel.getCodice()).dataIn(commessaModel.getDataIn()).dataOut(commessaModel.getDataOut())
				.importo(commessaModel.getImporto())
				.risorse(RisorsaDTO.createRisorsaDTOListFromModelList(commessaModel.getRisorse())).build();
		return result;
	}

	public static List<CommessaDTO> createCommessaDTOListFromModelList(List<Commessa> modelListInput) {
		return modelListInput.stream().map(commessaEntity -> {
			CommessaDTO result = CommessaDTO.buildCommessaDTOFromModel(commessaEntity);
			return result;
		}).collect(Collectors.toList());
	}

	public static List<Commessa> createCommessaListFromDTOList(List<CommessaDTO> modelListInput) {
		return modelListInput.stream().map(commessaDTOEntity -> {
			Commessa result = commessaDTOEntity.buildCommessaModel();
			return result;
		}).collect(Collectors.toList());
	}

	public Commessa buildCommessaModel() {
		Commessa result = Commessa.builder().id(this.id).descrizione(this.descrizione).codice(this.codice)
				.dataIn(this.dataIn).dataOut(this.dataOut).importo(this.importo)
				.risorse(RisorsaDTO.createRisorsaListFromDTOList(this.risorse)).build();

		return result;
	}

}
