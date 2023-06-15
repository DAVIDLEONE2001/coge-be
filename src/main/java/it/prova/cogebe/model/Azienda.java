package it.prova.cogebe.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "azienda")
public class Azienda {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "ragionerociale")
	private String ragioneSociale;
	@Column(name = "partitaiva")
	private String partitaIva;
	@Column(name = "indirizzo")
	private String indirizzo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "azienda")
	List<Commessa> commesse;
}
