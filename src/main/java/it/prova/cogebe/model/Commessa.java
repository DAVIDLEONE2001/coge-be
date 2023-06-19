package it.prova.cogebe.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "commessa")
public class Commessa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descrizione", length = 100)
	private String descrizione;

	@Column(name = "codice", length = 100)
	private String codice;

	@Column(name = "datain")
	private LocalDate dataIn;

	@Column(name = "dataout")
	private LocalDate dataOut;

	@Column(name = "importo")
	private Integer importo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "azienda_id", nullable = false)
	private Azienda azienda;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "commessa_risorsa", joinColumns = @JoinColumn(name = "commessa_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "risorsa_id", referencedColumnName = "id"))
	private List<Risorsa> risorse;
}
