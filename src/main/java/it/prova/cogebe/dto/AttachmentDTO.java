package it.prova.cogebe.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cogebe.model.Attachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AttachmentDTO {

	private Long id;
	private String fileName;
	private String contentType;
	private String decrizione;
	private LocalDate dataCreazione;
	private byte[] payload;
	
	private RisorsaDTO risorsa;


	public static AttachmentDTO buildAttachmentDTOFromModel(Attachment AttachmentModel) {

		AttachmentDTO result = AttachmentDTO.builder().id(AttachmentModel.getId())
				.fileName(AttachmentModel.getFileName()).contentType(AttachmentModel.getContentType())
				.decrizione(AttachmentModel.getDecrizione()).dataCreazione(AttachmentModel.getDataCreazione())
				.payload(AttachmentModel.getPayload()).risorsa(RisorsaDTO.buildRisorsaDTOFromModelSenzaCV(AttachmentModel.getRisorsa())).build();
		return result;
	}
	public static AttachmentDTO buildAttachmentDTOFromModelSenzaRisorsa(Attachment AttachmentModel) {
		
		AttachmentDTO result = AttachmentDTO.builder().id(AttachmentModel.getId())
				.fileName(AttachmentModel.getFileName()).contentType(AttachmentModel.getContentType())
				.decrizione(AttachmentModel.getDecrizione()).dataCreazione(AttachmentModel.getDataCreazione())
				.payload(AttachmentModel.getPayload()).build();
		return result;
	}

	public static List<AttachmentDTO> createAttachmentDTOListFromModelList(List<Attachment> modelListInput) {
		return modelListInput.stream().map(AttachmentEntity -> {
			AttachmentDTO result = AttachmentDTO.buildAttachmentDTOFromModel(AttachmentEntity);
			return result;
		}).collect(Collectors.toList());
	}
	
	public static List<Attachment> createAttachmentListFromDTOList(List<AttachmentDTO> DTOListInput) {
		return DTOListInput.stream().map(attachmentDTOEntity -> {
			Attachment result = attachmentDTOEntity.buildAttachmentModel();
			return result;
		}).collect(Collectors.toList());
	}

	public Attachment buildAttachmentModel() {
		Attachment result = Attachment.builder().id(this.id).fileName(this.fileName).contentType(this.contentType)
				.decrizione(this.decrizione).dataCreazione(this.dataCreazione).payload(this.payload).risorsa(this.risorsa.buildRisorsaModel()).build();

		return result;
	}
}
