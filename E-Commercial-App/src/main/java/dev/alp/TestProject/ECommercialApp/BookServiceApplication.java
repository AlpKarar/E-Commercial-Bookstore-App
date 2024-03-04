package dev.alp.TestProject.ECommercialApp;

import dev.alp.TestProject.ECommercialApp.Model.Book;
import dev.alp.TestProject.ECommercialApp.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookServiceApplication implements CommandLineRunner {

	@Value("${server.port}")
	private String PORT;

	private final BookRepository bookRepository;

    public BookServiceApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.printf("\nServer started running on port %s...\n\n", PORT);
		createDefaultBooksInStorage();
	}

	private void createDefaultBooksInStorage() {
		Book book1 = Book.builder()
				.id(1)
				.imageLink("http://abc.co/img-1")
				.title("War And Peace")
				.author("Tolstoy")
				.price(18.99)
				.stock(10)
				.build();

		Book book2 = Book.builder()
				.id(1)
				.imageLink("http://abc.co/img-2")
				.title("Karamazov Brothers")
				.author("Dostoevsky")
				.price(15.50)
				.stock(15)
				.build();

		Book book3 = Book.builder()
				.id(1)
				.imageLink("http://abc.co/img-1")
				.title("Anna Karenina")
				.author("Tolstoy")
				.price(23.00)
				.stock(8)
				.build();

		bookRepository.saveAll(List.of(book1, book2, book3));
	}
}
