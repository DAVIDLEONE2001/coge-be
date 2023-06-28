package it.prova.cogebe.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
// Json include, se un dato è nullo non cerrà messo nell'output
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommessaDTO {

	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	@NotBlank(message = "{codice.notblank}")
	private String codice;

	@NotNull(message = "{dataIn.notnull}")
	private LocalDate dataIn;
	@NotNull(message = "{dataOut.notnull}")
	private LocalDate dataOut;

	@NotNull(message = "{importo.notnull}")
	@Min(0)
	private Integer importo;

	private AziendaDTO azienda;

	private List<RisorsaDTO> risorse;

	public Commessa buildCommessaModel() {
		Commessa result = Commessa.builder().id(this.id).descrizione(this.descrizione).codice(this.codice)
				.dataIn(this.dataIn).dataOut(this.dataOut).importo(this.importo)
				.azienda(this.azienda.buildAziendaModel()).build();
		return result;
	}

	public static CommessaDTO buildCommessaDTOFromModel(Commessa commessaModel, boolean includeAzienda,
			boolean includeRisorse) {

		CommessaDTO result = CommessaDTO.builder().id(commessaModel.getId()).descrizione(commessaModel.getDescrizione())
				.codice(commessaModel.getCodice()).dataIn(commessaModel.getDataIn()).dataOut(commessaModel.getDataOut())
				.importo(commessaModel.getImporto()).build();
		if (includeAzienda) {
			result.setAzienda(AziendaDTO.buildAziendaDTOFromModel(commessaModel.getAzienda(), true));
		}
		if (includeRisorse) {
			result.setRisorse(RisorsaDTO.createRisorsaDTOListFromModelList(commessaModel.getRisorse()));
		}
		return result;
	}

	public static CommessaDTO buildCommessaDTOFromModelLazy(Commessa commessaModel) {

		return CommessaDTO.builder().id(commessaModel.getId()).descrizione(commessaModel.getDescrizione())
				.codice(commessaModel.getCodice()).dataIn(commessaModel.getDataIn()).dataOut(commessaModel.getDataOut())
				.importo(commessaModel.getImporto()).build();
	}

	public static Set<CommessaDTO> createCommessaDTOSetFromModelSet(Set<Commessa> modelListInput,
			boolean includeAzienda, boolean includeRisorse) {
		return modelListInput.stream().map(commessaItem -> {
			return CommessaDTO.buildCommessaDTOFromModel(commessaItem, includeAzienda, includeRisorse);
		}).collect(Collectors.toSet());
	}

	public static List<CommessaDTO> createCommessaDTOListFromModelSet(Set<Commessa> modelListInput,
			boolean includeAzienda, boolean includeRisorse) {
		return modelListInput.stream().map(commessaItem -> {
			return CommessaDTO.buildCommessaDTOFromModel(commessaItem, includeAzienda, includeRisorse);
		}).collect(Collectors.toList());
	}

	public static Set<Commessa> createCommessaSetFromDTOList(List<CommessaDTO> modelListInput) {
		return modelListInput.stream().map(commessaItem -> {
			return commessaItem.buildCommessaModel();
		}).collect(Collectors.toSet());
	}

	public static List<CommessaDTO> createCommessaDTOListFromModelList(List<Commessa> modelListInput,
			boolean includeAzienda, boolean includeRisorse) {
		return modelListInput.stream().map(inputEntity -> {
			return CommessaDTO.buildCommessaDTOFromModel(inputEntity, includeAzienda, includeRisorse);
		}).collect(Collectors.toList());
	}

	public static List<Commessa> createCommessaListFromDTOList(List<CommessaDTO> modelListInput, boolean eager) {
		return modelListInput.stream().map(CommessaDTO::buildCommessaModel).collect(Collectors.toList());
	}

	public static List<CommessaDTO> createCommessaDTOListFromModelList(Set<Commessa> commesse) {
		return commesse.stream().map(commessaItem -> CommessaDTO.buildCommessaDTOFromModel(commessaItem, true, true))
				.collect(Collectors.toList());
	}

}
