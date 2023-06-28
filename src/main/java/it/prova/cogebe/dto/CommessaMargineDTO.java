package it.prova.cogebe.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommessaMargineDTO {
	private String descrizione;
	private String codice;
	private LocalDate dataIn;
	private LocalDate dataOut;
	private Double importo;

	private Integer margine;

}
