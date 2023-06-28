package it.prova.cogebe;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.cogebe.model.Attachment;
import it.prova.cogebe.model.Azienda;
import it.prova.cogebe.model.Commessa;
import it.prova.cogebe.model.Rapportino;
import it.prova.cogebe.model.Risorsa;
import it.prova.cogebe.service.AttachmentService;
import it.prova.cogebe.service.AziendaService;
import it.prova.cogebe.service.CommessaService;
import it.prova.cogebe.service.RapportinoService;
import it.prova.cogebe.service.RisorsaService;

@SpringBootApplication
public class CogeBeApplication implements CommandLineRunner {

	
	@Autowired
	private RisorsaService risorsaService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private CommessaService commessaService;
	@Autowired
	private RapportinoService rapportinoService;	
	@Autowired
	private AziendaService aziendaService;

	public static void main(String[] args) {
		SpringApplication.run(CogeBeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.err.println("**********************************");

		FileInputStream fis = new FileInputStream("src/main/resources/stringtoolong.txt");
		String stringTooLong = IOUtils.toString(fis,Charsets.UTF_8);
	    //String stringTooLong = IOUtils.toString(fis, "UTF-8");
		byte[] payload = Base64.getDecoder().decode(stringTooLong);

		Attachment attachment1 = Attachment.builder().descrizione("cv di david").fileName("CV_Leone")
				.contentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document").payload(payload).dataCreazione(LocalDate.now()).build();

		Risorsa risorsa1 = Risorsa.builder().nome("David").cognome("Leone").codiceFiscale("LNEDVD01S04H501H")
				.costoGiornaliero(50).dataIn(LocalDate.parse("2020-01-01")).dataOut(LocalDate.now()).email("ciao@gmail.com").build();

		risorsaService.inserisciNuovo(risorsa1);
		attachmentService.inserisciNuovo(attachment1);
		risorsa1.setCv(attachment1);
		attachment1.setRisorsa(risorsa1);
		risorsaService.aggiorna(risorsa1);
		attachmentService.aggiorna(attachment1);

		
		FileInputStream fis2 = new FileInputStream("src/main/resources/stringtoolong.txt");
		String stringTooLong2 = IOUtils.toString(fis2, "UTF-8");	
		byte[] payload2 = Base64.getDecoder().decode(stringTooLong2);
		
		
		Attachment attachment2 = Attachment.builder().descrizione("cv di cristin").fileName("CV_Cristan")
				.contentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document").payload(payload2).dataCreazione(LocalDate.now()).build();

		Risorsa risorsa2 = Risorsa.builder().nome("Cristian").cognome("Piarulli").codiceFiscale("PRLCST97A25H501R")
				.costoGiornaliero(50).dataIn(LocalDate.parse("2020-01-01")).email("ciao2@gmail.com").build();

		risorsaService.inserisciNuovo(risorsa2);
		attachmentService.inserisciNuovo(attachment2);
		risorsa2.setCv(attachment2);
		attachment2.setRisorsa(risorsa2);
		risorsaService.aggiorna(risorsa2);
		attachmentService.aggiorna(attachment2);

		
		//Creazione Azienda
		
		
		Azienda azienda1 = Azienda.builder().ragioneSociale("Azienda 4Piano").partitaIva("PPIVA")
				.indirizzo("Via Mosca 52").build();
		aziendaService.inserisciNuovo(azienda1);
		Azienda azienda2 = Azienda.builder().ragioneSociale("Azienda 7Piano").partitaIva("CCIVA")
				.indirizzo("Via Mosca 52").build();
		aziendaService.inserisciNuovo(azienda2);
		
		
		// -----------------------------------------------------------------------------------------
		
		
		Commessa commessa = Commessa.builder().descrizione("Descrizione commessa di prova").codice("COD-001")
				.dataIn(LocalDate.parse("2023-06-15")).dataOut(LocalDate.parse("2023-06-30")).importo(1000)
				.azienda(azienda1).risorse(Arrays.asList(risorsa1)).build();
		commessaService.inserisciNuovo(commessa);

		Commessa commessa2 = Commessa.builder().descrizione("Descrizione altra commessa di prova").codice("COD-002")
				.dataIn(LocalDate.parse("2023-07-01")).dataOut(LocalDate.parse("2023-07-15")).importo(1500)
				.azienda(azienda2).risorse(Arrays.asList(risorsa2)).build();
		commessaService.inserisciNuovo(commessa2);

		Rapportino rapportino = Rapportino.builder().numeroGiorni(5).risorsa(risorsa1).commessa(commessa).build();
		rapportinoService.inserisciNuovo(rapportino);

		Rapportino rapportino2 = Rapportino.builder().numeroGiorni(3).risorsa(risorsa2).commessa(commessa2).build();
		rapportinoService.inserisciNuovo(rapportino2);

		System.err.println("**********************************");
		
	}

}
