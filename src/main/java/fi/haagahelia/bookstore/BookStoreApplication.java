package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.repository.BookRepository;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
    public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			repository.save(new Book(
					"A Farewell to Arms",
					"Ernest Hemingway",
					1929,
					"1232323-21",
					12.99));

			repository.save(new Book(
					"Animal Farm",
					"George Orwell",
					1945,
					"2212343-5",
					9.99));
		};
	}

}
