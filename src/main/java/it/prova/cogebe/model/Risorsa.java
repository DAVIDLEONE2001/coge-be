package it.prova.cogebe.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "risorsa")
public class Risorsa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "datain")
	private LocalDate dataIn;
	@Column(name = "dataout")
	private LocalDate dataOut;
	@Column(name = "codicefiscale")
	private String codiceFiscale;
	@Column(name = "email")
	private String email;
	@Column(name = "costogiornaliero")
	private Integer costoGiornaliero;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "risorsa")
	private Attachment cv;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "risorse")
	private List<Commessa> commesse;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commessa")
	private List<Rapportino> rapportini;

}
