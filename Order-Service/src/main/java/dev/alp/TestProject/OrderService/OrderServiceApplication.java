package dev.alp.TestProject.OrderService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApplication implements CommandLineRunner {

	@Value("${server.port}")
	private String PORT;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.printf("\nOrder Service started running on port %s...\n", PORT);
	}
}
