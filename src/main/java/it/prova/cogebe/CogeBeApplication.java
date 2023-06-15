package it.prova.cogebe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CogeBeApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CogeBeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.err.println("**********************************");
		System.err.println("**********************************");
		
	}

}
