package it.prova.cogebe.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cogebe.model.Risorsa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class RisorsaDTO {

	private Long id;
	private String nome;
	private String cognome;
	private LocalDate dataIn;
	private LocalDate dataOut;
	private String codiceFiscale;
	private String email;
	private Integer costoGiornaliero;

	private AttachmentDTO cv;

	private List<CommessaDTO> commesse;

//	qua servono i vostri DTO
	private List<RapportinoDTO> rapportini;

	public static RisorsaDTO buildRisorsaDTOFromModel(Risorsa risorsaModel) {

		RisorsaDTO result = RisorsaDTO.builder().id(risorsaModel.getId()).nome(risorsaModel.getNome())
				.cognome(risorsaModel.getCognome()).dataIn(risorsaModel.getDataIn()).dataOut(risorsaModel.getDataOut())
				.codiceFiscale(risorsaModel.getCodiceFiscale()).email(risorsaModel.getEmail())
				.costoGiornaliero(risorsaModel.getCostoGiornaliero()).build();
		if (risorsaModel.getCommesse() != null) {
//			result.setCommesse(CommessaDTO.createCommessaDTOListFromModelList(risorsaModel.getCommesse()));
		}
		if (risorsaModel.getRapportini() != null) {
//			result.setRapportini(RapportinoDTO.createRapportinoDTOListFromModelList(risorsaModel.getRapportini()));
		}
		if (risorsaModel.getCv() != null) {
			result.setCv(AttachmentDTO.buildAttachmentDTOFromModel(risorsaModel.getCv()));
		}

		return result;
	}

	public static RisorsaDTO buildRisorsaDTOFromModelSenzaCV(Risorsa risorsaModel) {

		RisorsaDTO result = RisorsaDTO.builder().id(risorsaModel.getId()).nome(risorsaModel.getNome())
				.cognome(risorsaModel.getCognome()).dataIn(risorsaModel.getDataIn()).dataOut(risorsaModel.getDataOut())
				.codiceFiscale(risorsaModel.getCodiceFiscale()).email(risorsaModel.getEmail())
				.costoGiornaliero(risorsaModel.getCostoGiornaliero()).build();

		if (risorsaModel.getCommesse() != null) {
			result.setCommesse(CommessaDTO.createCommessaDTOListFromModelList(risorsaModel.getCommesse()));
		}
		if (risorsaModel.getRapportini() != null) {
			result.setRapportini(RapportinoDTO.createRapportinoDTOListFromModelList(risorsaModel.getRapportini()));
		}

		return result;
	}
	public static RisorsaDTO buildRisorsaDTOFromModelLazy(Risorsa risorsaModel) {
		
		RisorsaDTO result = RisorsaDTO.builder().id(risorsaModel.getId()).nome(risorsaModel.getNome())
				.cognome(risorsaModel.getCognome()).dataIn(risorsaModel.getDataIn()).dataOut(risorsaModel.getDataOut())
				.codiceFiscale(risorsaModel.getCodiceFiscale()).email(risorsaModel.getEmail())
				.costoGiornaliero(risorsaModel.getCostoGiornaliero()).build();
		
		
		return result;
	}

	public static List<RisorsaDTO> createRisorsaDTOListFromModelList(List<Risorsa> modelListInput) {
		return modelListInput.stream().map(atletaEntity -> {
			RisorsaDTO result = RisorsaDTO.buildRisorsaDTOFromModel(atletaEntity);
			return result;
		}).collect(Collectors.toList());
	}

	public static List<Risorsa> createRisorsaListFromDTOList(List<RisorsaDTO> modelListInput) {
		return modelListInput.stream().map(risorsaDTOEntity -> {
			Risorsa result = risorsaDTOEntity.buildRisorsaModel();
			return result;
		}).collect(Collectors.toList());
	}

	public Risorsa buildRisorsaModel() {
		Risorsa result = Risorsa.builder().id(this.id).nome(this.nome).cognome(this.cognome).dataIn(this.dataIn)
				.dataOut(this.dataOut).codiceFiscale(this.codiceFiscale).email(this.email)
				.costoGiornaliero(this.costoGiornaliero).build();

		if (this.cv != null) {
			result.setCv(this.cv.buildAttachmentModel());
		}
		if (this.commesse != null) {
			result.setCommesse(CommessaDTO.createCommessaListFromDTOList(this.commesse));
		}
		if (this.rapportini != null) {
			result.setRapportini(RapportinoDTO.createRapportinoListFromDTOList(this.rapportini));
		}

		return result;
	}
}
