package dev.alp.TestProject.ECommercialApp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommercialAppApplication implements CommandLineRunner {

	@Value("${server.port}")
	private String PORT;

	public static void main(String[] args) {
		SpringApplication.run(ECommercialAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.printf("\nServer started running on port %s...\n\n", PORT);
	}
}
