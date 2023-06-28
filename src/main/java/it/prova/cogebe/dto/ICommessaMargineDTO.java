package it.prova.cogebe.dto;

import java.time.LocalDate;

public interface ICommessaMargineDTO {
	String getDescrizione();

	String getCodice();

	LocalDate getData_in();

	LocalDate getData_out();

	Double getImporto();

	Integer getMargine();

}