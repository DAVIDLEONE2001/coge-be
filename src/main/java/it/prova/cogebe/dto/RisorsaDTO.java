package it.prova.cogebe.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import it.prova.cogebe.model.Attachment;
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

	private List<Commessa> commesse;

//	qua servono i vostri DTO
	private List<Rapportino> rapportini;
	public static RisorsaDTO buildRisorsaDTOFromModel(Risorsa risorsaModel) {

		RisorsaDTO result = RisorsaDTO.builder().id(risorsaModel.getId()).nome(risorsaModel.getNome())
				.cognome(risorsaModel.getCognome()).dataIn(risorsaModel.getDataIn()).dataOut(risorsaModel.getDataOut())
				.codiceFiscale(risorsaModel.getCodiceFiscale()).email(risorsaModel.getEmail())
				.costoGiornaliero(risorsaModel.getCostoGiornaliero())
				.cv(AttachmentDTO.buildAttachmentDTOFromModel(risorsaModel.getCv())).build();
		return result;
	}

	public static List<RisorsaDTO> createRisorsaDTOListFromModelList(List<Risorsa> modelListInput) {
		return modelListInput.stream().map(atletaEntity -> {
			RisorsaDTO result = RisorsaDTO.buildRisorsaDTOFromModel(atletaEntity);
			return result;
		}).collect(Collectors.toList());
	}

	public Risorsa buildRisorsaModel() {
		Risorsa result = Risorsa.builder().id(this.id).nome(this.nome).cognome(this.cognome).dataIn(this.dataIn)
				.dataOut(this.dataOut).codiceFiscale(this.codiceFiscale).email(this.email)
				.costoGiornaliero(this.costoGiornaliero).cv(this.cv.buildAttachmentModel()).build();

		return result;
	}
}
