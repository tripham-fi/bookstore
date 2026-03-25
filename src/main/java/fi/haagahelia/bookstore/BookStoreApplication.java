package fi.haagahelia.bookstore;


import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.repository.BookRepository;
import fi.haagahelia.bookstore.repository.CategoryRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (userRepository.findByUsername("user") == null) {
				userRepository.save(new User(
						"user",
						encoder.encode("user123"),
						"USER",
						"user@email.com"
				));
			}
			if (userRepository.findByUsername("admin") == null) {
				userRepository.save(new User(
						"admin",
						encoder.encode("admin123"),
						"ADMIN",
						"admin@email.com"
				));
			}

			Category cate1 = new Category("Fantasy");
			Category cate2 = new Category("Historical");
			Category cate3 = new Category("Drama");

			if (categoryRepository.count() == 0) {
				categoryRepository.save(cate1);
				categoryRepository.save(cate2);
				categoryRepository.save(cate3);
			}
			bookRepository.save(new Book(
					"A Farewell to Arms",
					"Ernest Hemingway",
					1929,
					"1232323-21",
					12.99,
					cate1));

			bookRepository.save(new Book(
					"Animal Farm",
					"George Orwell",
					1945,
					"2212343-5",
					9.99,
					cate2));
		};
	}

}
