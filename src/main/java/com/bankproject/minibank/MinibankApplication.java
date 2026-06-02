package com.bankproject.minibank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class MinibankApplication {
	@org.springframework.beans.factory.annotation.Value("${server.port:8080}")
	private String port;

	public static void main(String[] args) {
		SpringApplication.run(MinibankApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(KontoRepository repository) {
		return (args) -> {
			Konto janusz = new Konto();
			janusz.setImie("Janusz");
			janusz.setNazwisko("Kowalski");
			janusz.setNrKonta("11111111111");
			janusz.wplac(BigDecimal.valueOf(1000.10));
			janusz.setHaslo(Szyfrowanie.haszuj("123"));
			repository.save(janusz);


			Konto grazyna = new Konto();
			grazyna.setImie("Grazyna");
			grazyna.setNazwisko("Nowak");
			grazyna.setNrKonta("22222222222");
			grazyna.setHaslo(Szyfrowanie.haszuj("haslo"));
			grazyna.wplac(BigDecimal.valueOf(300.03));
			repository.save(grazyna);


			System.out.println("\n==================================================");
			System.out.println(" Aby otworzyć bank, wejdź na: http://localhost:" + port);
			System.out.println("==================================================\n");
		};
	}
}