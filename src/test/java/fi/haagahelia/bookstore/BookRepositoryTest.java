package fi.haagahelia.bookstore;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.repository.BookRepository;
import fi.haagahelia.bookstore.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Book savedBook;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        categoryRepository.deleteAll();

        Category category = categoryRepository.save(new Category("Fiction"));
        savedBook = bookRepository.save(new Book(
                "Clean Code", "Robert C. Martin", 2008, "978-0132350884", 29.99, category
        ));
    }

    @Test
    public void testCreateBook() {
        Category category = new Category("Test Category");
        categoryRepository.save(category);

        Book book = new Book("Test Book", "Author", 2026, "1111", 99.99, category);
        bookRepository.save(book);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void testFindByTitle() {

        List<Book> books = bookRepository.findByTitleContainingIgnoreCase("clean");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo(savedBook.getAuthor());
    }

    @Test
    public void testUpdateBook() {
        assertThat(savedBook.getTitle()).isEqualTo("Clean Code");
        assertThat(savedBook.getPrice()).isEqualTo(29.99);
        savedBook.setTitle("Clean Work");
        savedBook.setPrice(2.00);
        bookRepository.save(savedBook);
        assertThat(savedBook.getAuthor()).isNotEqualTo("Clean Code");
        assertThat(savedBook.getPrice()).isNotEqualTo(29.99);
        assertThat(savedBook.getAuthor()).isEqualTo("Robert C. Martin");
    }

    @Test
    public void testDeleteBook() {
        Category category = new Category("Drama");
        categoryRepository.save(category);

        Book book = new Book("Delete Test", "Author", 2021, "999", 12.0, category);
        bookRepository.save(book);

        Long id = book.getId();

        bookRepository.deleteById(id);

        assertThat(bookRepository.findById(id)).isEmpty();
    }

}
