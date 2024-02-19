package dev.alp.TestProject.WishlistService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WishlistServiceApplication implements CommandLineRunner {

	@Value("${server.port}")
	private String PORT;

	public static void main(String[] args) {
		SpringApplication.run(WishlistServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.printf("Wishlist Service started running on port %s...", PORT);
	}
}
