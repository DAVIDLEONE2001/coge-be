package it.prova.cogebe.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)

public class RisorsaDTOInsert {

	private Long id;
	private String nome;
	private String cognome;
	private LocalDate dataIn;
	private LocalDate dataOut;
	private String codiceFiscale;
	private String email;
	private Integer costoGiornaliero;

	private AttachmentDTO cv;

	private List<Long> idsCommesse;

//	qua servono i vostri DTO
	private List<Long> idsRapportini;


	public static List<Risorsa> createRisorsaListFromDTOList(List<RisorsaDTOInsert> modelListInput) {
		return modelListInput.stream().map(risorsaDTOEntity -> {
			Risorsa result = risorsaDTOEntity.buildRisorsaModel();
			return result;
		}).collect(Collectors.toList());
	}

	public Risorsa buildRisorsaModel() {
		Risorsa result = Risorsa.builder().id(this.id).nome(this.nome).cognome(this.cognome).dataIn(this.dataIn)
				.dataOut(this.dataOut).codiceFiscale(this.codiceFiscale).email(this.email).rapportini(new ArrayList<>()).commesse(new ArrayList<>())
				.costoGiornaliero(this.costoGiornaliero).build();

		if (this.cv != null) {
			result.setCv(this.cv.buildAttachmentModel());
		}
		if (this.idsCommesse != null) {
			idsCommesse.forEach(i->result.getCommesse().add(Commessa.builder().id(i).build()));
		}
		if (this.idsRapportini != null) {
			idsRapportini.forEach(i->result.getRapportini().add(Rapportino.builder().id(i).build()));
		}

		return result;
	}
}
